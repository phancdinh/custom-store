package org.wso2.sample.user.store.manager;

import org.apache.axiom.om.util.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.wso2.carbon.user.api.RealmConfiguration;
import org.wso2.carbon.user.api.UserStoreManager;
import org.wso2.carbon.user.core.UserCoreConstants;
import org.wso2.carbon.user.core.UserStoreException;
import org.wso2.carbon.user.core.claim.ClaimManager;
import org.wso2.carbon.user.core.jdbc.JDBCRealmConstants;
import org.wso2.carbon.user.core.jdbc.UniqueIDJDBCUserStoreManager;
import org.wso2.carbon.user.core.profile.ProfileConfigurationManager;
import org.wso2.carbon.utils.Secret;
import org.wso2.carbon.utils.UnsupportedSecretTypeException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;


/**
 * Sample User Store Manager Class
 */
public class CustomUserStoreManager2 extends UniqueIDJDBCUserStoreManager implements UserStoreManager{
    private static Log log = LogFactory.getLog(CustomUserStoreManager2.class);
    // This instance is used to generate the hash values
//    private static StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

    public CustomUserStoreManager2() {
    }

    // You must implement at least one constructor
    public CustomUserStoreManager2(RealmConfiguration realmConfig, Map<String, Object> properties, ClaimManager
            claimManager, ProfileConfigurationManager profileManager, org.wso2.carbon.user.core.UserRealm realm, Integer tenantId)
            throws UserStoreException {
        super(realmConfig, properties, claimManager, profileManager, realm, tenantId);
        log.info("CustomUserStoreManager  Class initialized...");
    }

//    @Override
//    public boolean doAuthenticate(String userName, Object credential) throws UserStoreException {
//        boolean isAuthenticated = false;
//        log.debug("doAuthenticate CustomUserStoreManager  " + userName);
//        log.debug(credential);
//        if (userName != null && credential != null) {
//            Connection dbConnection = null;
//            try {
//                String candidatePassword = String.copyValueOf(((Secret) credential).getChars());
//
//                ResultSet rs = null;
//                PreparedStatement prepStmt = null;
//                String sql = null;
//                dbConnection = this.getDBConnection();
//                dbConnection.setAutoCommit(false);
//                // get the SQL statement used to select user details
////                sql = this.realmConfig.getUserStoreProperty("SelectUserSQL");
//                sql = "SELECT * FROM UM_USER WHERE (UM_USER_NAME=? OR UM_USER_EMAIL=? OR UM_USER_PHONE=?) AND UM_TENANT_ID=?";
//                if (log.isDebugEnabled()) {
//                    log.debug(sql);
//                }
//
//                prepStmt = dbConnection.prepareStatement(sql);
//                prepStmt.setString(1, userName);
//                prepStmt.setString(2, userName);
//                prepStmt.setString(3, userName);
//                // check whether tenant id is used
//                if (sql.contains("UM_TENANT_ID")) {
//                    prepStmt.setInt(4, this.tenantId);
//                }
//
//                rs = prepStmt.executeQuery();
//                if (rs.next()) {
//                    String storedPassword = rs.getString(4);
//
//                    // check whether password is expired or not
//                    boolean requireChange = rs.getBoolean(6);
//                    Timestamp changedTime = rs.getTimestamp(7);
//                    GregorianCalendar gc = new GregorianCalendar();
//                    gc.add(GregorianCalendar.HOUR, -24);
//                    Date date = gc.getTime();
//                    if (!(requireChange && changedTime.before(date))) {
//                        // compare the given password with stored password using jasypt
//                        isAuthenticated = passwordEncryptor.checkPassword(candidatePassword, storedPassword);
//                    }
//                }
//                dbConnection.commit();
//                log.info(userName + " is authenticated? " + isAuthenticated);
//            } catch (SQLException exp) {
//                try {
//                    dbConnection.rollback();
//                } catch (SQLException e1) {
//                    throw new UserStoreException("Transaction rollback connection error occurred while" +
//                            " retrieving user authentication info. Authentication Failure.", e1);
//                }
//                log.error("Error occurred while retrieving user authentication info.", exp);
//                throw new UserStoreException("Authentication Failure");
//            }
//        }
//        return isAuthenticated;
//    }
//
//    @Override
//    protected String preparePassword(Object password, String saltValue) throws UserStoreException {
//
//        Secret credentialObj;
//        try {
//            credentialObj = Secret.getSecret(password);
//        } catch (UnsupportedSecretTypeException e) {
//            throw new UserStoreException("Unsupported credential type", e);
//        }
//        try {
//            String passwordString;
//            if (saltValue != null) {
//                credentialObj.addChars(saltValue.toCharArray());
//            }
//
//            String digestFunction = realmConfig.getUserStoreProperties().get(JDBCRealmConstants.DIGEST_FUNCTION);
//            if (digestFunction != null) {
//                if (digestFunction.equals(UserCoreConstants.RealmConfig.PASSWORD_HASH_METHOD_PLAIN_TEXT)) {
//                    passwordString = new String(credentialObj.getChars());
//                    return passwordString;
//                }
//
//                MessageDigest digest = MessageDigest.getInstance(digestFunction);
//                byte[] byteValue = digest.digest(credentialObj.getBytes());
//                passwordString = Base64.encode(byteValue);
//            } else {
//                passwordString = new String(credentialObj.getChars());
//            }
//
//            return passwordString;
//        } catch (NoSuchAlgorithmException e) {
//            String msg = "Error occurred while preparing password.";
//            if (log.isDebugEnabled()) {
//                log.debug(msg, e);
//            }
//            throw new UserStoreException(msg, e);
//        } finally {
//            credentialObj.clear();
//        }
//    }
}