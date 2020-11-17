package org.wso2.sample.user.store.manager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.wso2.carbon.user.api.*;
import org.wso2.carbon.user.core.UserStoreException;
import org.wso2.carbon.user.core.claim.ClaimManager;
import org.wso2.carbon.user.core.jdbc.JDBCUserStoreManager;

import java.util.Date;
import java.util.Map;


/**
 * Sample User Store Manager Class
 */
public class CustomUserStoreManager extends JDBCUserStoreManager implements UserStoreManager{
    private static Log log = LogFactory.getLog(CustomUserStoreManager.class);
    // This instance is used to generate the hash values

    public CustomUserStoreManager() {
    }
    @Override
    public boolean doAuthenticate(String userName, Object credential) throws UserStoreException {
        return true;
    }

    public Properties getDefaultUserStoreProperties() {
        Properties properties = new Properties();
        properties.setMandatoryProperties(CustomUserStoreManagerConstants.CUSTOM_USERSTORE_PROPERTIES.toArray
                (new Property[CustomUserStoreManagerConstants.CUSTOM_USERSTORE_PROPERTIES.size()]));
        properties.setOptionalProperties(CustomUserStoreManagerConstants.OPTIONAL_CUSTOM_USERSTORE_PROPERTIES.toArray
                (new Property[CustomUserStoreManagerConstants.OPTIONAL_CUSTOM_USERSTORE_PROPERTIES.size()]));
        return properties;
    }

}