package idnewMaven.idMaven;

import java.util.Properties;

import iot.jcypher.database.DBAccessFactory;
import iot.jcypher.database.DBProperties;
import iot.jcypher.database.DBType;
import iot.jcypher.database.IDBAccess;

public class ConnectionBD {

    private static IDBAccess dbAccess = null;

	public static IDBAccess getConnection() {
        
    	if (dbAccess == null) {
        	
        	
        	Properties props = new Properties();
            
            props.setProperty(DBProperties.SERVER_ROOT_URI, "http://localhost:7474");
            
            dbAccess =
            		   DBAccessFactory.createDBAccess(DBType.REMOTE, props);
            		
            
        }
        return dbAccess;
    }
    
}
