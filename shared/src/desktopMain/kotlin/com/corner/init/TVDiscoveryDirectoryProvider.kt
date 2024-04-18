package com.corner.init

import uk.co.caprica.vlcj.binding.support.runtime.RuntimeUtil
import uk.co.caprica.vlcj.factory.discovery.provider.DiscoveryDirectoryProvider
import uk.co.caprica.vlcj.factory.discovery.provider.DiscoveryProviderPriority
import java.io.File

/**
 * vlc discovery
 */
class TVDiscoveryDirectoryProvider:DiscoveryDirectoryProvider {
    private val resourcesDir = File(System.getProperty("compose.application.resources.dir"))

    override fun priority(): Int {
        return DiscoveryProviderPriority.CONFIG_FILE
    }

    override fun directories(): Array<String> {
        return resourcesDir.listFiles { _, name -> name.lowercase().matches(Regex("vlc")) }?.map { it.path }?.toTypedArray() ?: arrayOf()
    }

    override fun supported(): Boolean {
        return RuntimeUtil.isWindows()
    }
}