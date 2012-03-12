
package org.mule.munit.config;

import org.mule.api.Capabilities;
import org.mule.api.Capability;


/**
 * A <code>DBServerModuleCapabilitiesAdapter</code> is a wrapper around {@link org.mule.munit.DBServerModule } that implements {@link org.mule.api.Capabilities} interface.
 * 
 */
public class DBServerModuleCapabilitiesAdapter
    extends org.mule.munit.DBServerModule
    implements Capabilities
{


    /**
     * Returns true if this module implements such capability
     * 
     */
    public boolean isCapableOf(Capability capability) {
        if (capability == Capability.LIFECYCLE_CAPABLE) {
            return true;
        }
        return false;
    }

}
