package idnewMaven.idMaven;

import java.util.List;

import org.neo4j.cypher.internal.compiler.v3_1.planner.logical.plans.SetProperty;

import iot.jcypher.database.IDBAccess;
import iot.jcypher.graph.GrNode;
import iot.jcypher.query.JcQuery;
import iot.jcypher.query.JcQueryResult;
import iot.jcypher.query.api.IClause;
import iot.jcypher.query.factories.clause.CREATE;
import iot.jcypher.query.factories.clause.DO;
import iot.jcypher.query.factories.clause.MATCH;
import iot.jcypher.query.factories.clause.RETURN;
import iot.jcypher.query.values.JcNode;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	long start = System.currentTimeMillis();
    	
    	CreateDataSet createDataSet = new CreateDataSet();
    	/* Carregamento das Sentenças, onde os parâmetros passados
    	     são a quantidade de Sentenças Carregadas no Banco Neo4J*/
    	createDataSet.CarregarTokens(108);
    	createDataSet.CarregarChunks(108);
    	createDataSet.CarregarChunkSucc(108);
    	createDataSet.CarregarNextToken(108);
    	createDataSet.CarregarDependencias(108);

    	long elapsed = System.currentTimeMillis() - start;
    	
    	System.out.println("Tempo de Execução Carga DataSet: "+ elapsed);
    	
    	/*
    	IDBAccess  dbAccess = ConnectionBD.getConnection();		
        
        JcNode actor = new JcNode("Person"); 
        
        JcQuery query = new JcQuery(); 
        query.setClauses(new IClause[] { 
        		
        		CREATE.node(actor).label("pessoa").property("lastname").value("Shakespeare"),
        		DO.SET(actor.property("rua")).to("lua")
        }); 	
        JcQueryResult result = dbAccess.execute(query); 
        List<GrNode> actors = result.resultOf(actor);
        
        for (GrNode grNode : actors) {
			System.out.println(grNode.getProperty("name"));
		}
		*/
    }
}
