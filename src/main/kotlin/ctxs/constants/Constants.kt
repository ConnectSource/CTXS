package ctxs.constants

/** Constants */
object Constants {

     /** Override your own CTHx version here, if not, CTXS will use the latest CTHx version. (updated manually by ConnectSource admins)
      * CAUTION: CTHx will prompt to update if the version does not math this one's version. */
     const val VERSION : String = "0.0.1"


    /** Override, if not it'll default to 80.
     * CAUTION: Remember that server port 80 is the default http port, and port 443 is the default HTTPS port. */
    const val HTTP_PORT : Int = 80


    /** Override your own ctUI bundle URL here, if not, CTXS will use the official ctUI URL. (controlled by ConnectSource admins)
     * CAUTION: ctUIs have complete access. Only use ctUIs that you trust. */
    const val GUI_LINK : String = "about:blank"


    /** Set this to true if you would like to unminify ctxs.main.js
     * CAUTION: this will HEAVILY increase the filesize of ctxs.main.js- use for debugging ONLY. */
    const val UNMINIFY_SOURCE : Boolean = false


    /** Override your own License links here, otherwise ConnectSource's license repo will be used
     * CAUTION: If your license is incompatible with ours, then you are violating our license and may not use ConnectSource software. */
    const val LICENSE_LINK : String = "https://github.com/DxltaMath/license";

}
