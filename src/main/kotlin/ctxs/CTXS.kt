package ctxs

import java.lang.Thread.sleep
import ctxs.constants.Constants as constants
import ctxs.func.fetch
import ctxs.patching.Patches
import ctxs.patching.Variables
import java.util.*

/** The actual ConnectTeam script patcher. */
class CTXS {

    /** Latest unmodified main.js */
    private var latestVanillaFile : String? = null;

    /** Latest patched main.js */
    private var latestPatchedFile : String? = null;

    /** ConnectTeam main.js URL */
    val mainJsUrl : String = "https://app.connecteam.com/main.js";


    /**
     * Applies patches to (unmodifiedFile).
     * @param unmodifiedFile ConnectTeam's unmodified main.js file to patch
     * @author gemsvidø
     * @return The modified ConnectTeam main.js file
     */
    fun patchFile (unmodifiedFile : String) : String {


        println("[patchFile] Patching main.js...")

        val variables : Variables = Variables();

        val patches : Patches = Patches(unmodifiedFile);


        val output : String = """/* main.js - ${Date(System.currentTimeMillis()).toString()} */
            
            ${variables.get() /* Accessors */}
            
            window.oldLodash = window._;
			let lodashChecker = setInterval(() => {
				if (window.oldLodash !== window._) {
					window._ = window.oldLodash;
					clearInterval(lodashChecker);
				}
			});
            
            ${patches.get() /* Patched main.js */}
            
            
            console.log("%cCTXS", "font-size:69px;color:#540052;font-weight:900;font-family:sans-serif;");
			console.log("%cVersion ${constants.VERSION}", "font-size:20px;color:#000025;font-weight:700;font-family:sans-serif;");
			
			/* Load the ConnectTeam hack GUI */
			(async () => {
				await new Promise(r => setTimeout(r, 5000));
				await eval(await (await fetch("${constants.GUI_LINK}")).text());
			})();
			console.trace = () => {};
        """.trimIndent();

        println("[patchFile] Successfully patched main.js")
        return output;
    }



    /**
     * Gets the latest non-modified main.js file. If it isn't cached, download it.
     * @author gemsvidø
     * @return ConnectTeam's non-modified main.js
     */
    fun getFile () : String {
        if (latestVanillaFile === null) {
            println("[getFile] Main.js contents is not cached. Fetching it...")
            latestVanillaFile = fetch(mainJsUrl);
        } else {
            println("[getFile] Main.js contents is cached.")
        }
        println("[getFile] Successfully obtained main.js contents.")
        return latestVanillaFile!!;
    }

    /**
     * Gets the latest patched main.js file. If it isn't cached, patch it.
     * @author gemsvidø
     * @return The latest patched main.js file
     */
    fun getPatchedFile () : String {
        if (latestPatchedFile === null) {
            println("[getPatchedFile] Patched main.js is not cached. Patching now...")
            latestPatchedFile = patchFile(getFile())
        } else {
            println("[getPatchedFile] Patched main.js is cached.")
        }
        println("[getPatchedFile] Successfully obtained patched main.js")
        return latestPatchedFile!!;
    }


    /**
     * Clears the caches every cacheInterval
     * @author gemsvidø
     */
    fun startCaching () : Unit {

        val cacheInterval : Long = 20 * 60 * 1000;



        val latestVanillaCache = Thread {
            println("[startCaching] Purging vanilla file cache every $cacheInterval milliseconds")
            while (true) {
                sleep(cacheInterval)
                latestVanillaFile = null;
            }
        }
        latestVanillaCache.start();


        val latestPatchedCache = Thread {
            println("[startCaching] Purging patched file cache every $cacheInterval milliseconds")
            while (true) {
                sleep(cacheInterval)
                latestPatchedFile = null;
            }
        }
        latestPatchedCache.start();


    }


}