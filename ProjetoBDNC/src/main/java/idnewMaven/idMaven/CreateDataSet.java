package idnewMaven.idMaven;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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

public class CreateDataSet {

	
	
	public void CarregarTokens(int qtdSentences) {
			
		 FileReader arq;
		 IDBAccess  dbAccess = ConnectionBD.getConnection();		
	        
		try {
			arq = new FileReader(new File("CorpusACE2004_FULL.pl"));
		     BufferedReader lerArq = new BufferedReader(arq);
		     
		      String linha = lerArq.readLine(); 
		      int countSentences = 1;
		      
		      while (linha != null) {
		    	  
		        linha = lerArq.readLine(); 
		        
		        
		        if(linha!=null) {
		        	
		        	if(linha.split("[\\W]")[0].equals("token")) {
		        		
		        		JcNode token = new JcNode("token"); 
		                

				        String[] string = linha.split("[(,).]");
				        
		                JcQuery query = new JcQuery(); 
		                query.setClauses(new IClause[] { 
		                		
		                		CREATE.node(token).label("Token"),
		                		DO.SET(token.property("name")).to(string[1])

		                }); 	
		                JcQueryResult result = dbAccess.execute(query); 
		                
		                String id = string[1];
		     		        		
		        		while(linha.length()!= 0) {
		        			
		        			linha = lerArq.readLine();
					        linha = linha.replaceAll(" ", "");
					        
					        string = linha.split("[(,).]");
					     
					     if(string.length >2) {
					    	 
					    	 query.setClauses(new IClause[] { 
				                		
				                		MATCH.node(token).label("Token").property("name").value(id),
				                		DO.SET(token.property(string[0])).to(string[2])
				                }); 	
				                 
					     }else if(string.length==2) {
					    	 
					    	 query.setClauses(new IClause[] { 
				                		
				                		MATCH.node(token).label("Token").property("name").value(id),
				                		DO.SET(token.property(string[0])).to(string[1])
				                }); 
					     }   
					        
					        result = dbAccess.execute(query); 
			                
					        		        			
		        		}
		        		
		        		

					
		        		
		        	}else if(linha.split("[().]")[0].equals("%st")) {
			        	
		        		if(countSentences==qtdSentences) {

		        			break;
		        			
			        	}
		        		countSentences++;
		        		
		        	}
		         }
		        
		        }
		      

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	}

	public void CarregarChunks(int qtdSentences) {

		 FileReader arq;
		 IDBAccess  dbAccess = ConnectionBD.getConnection();		
	        
		try {
			arq = new FileReader(new File("CorpusACE2004_FULL.pl"));
		     BufferedReader lerArq = new BufferedReader(arq);
		      String linha = lerArq.readLine(); 
		      int countSentences = 1;
		      
		      while (linha != null) {
		    	  
		        linha = lerArq.readLine(); 
		        
		        
		        if(linha!=null) {
		        	
		        	if(linha.split("[\\W]")[0].equals("chunk")) {
		        		
		        		
		        		JcNode chunk = new JcNode("Chunk"); 
		        		JcNode token = new JcNode("Token");

				        String[] string = linha.split("[(,).]");
				        
		                JcQuery query = new JcQuery(); 
		                query.setClauses(new IClause[] { 
		                		
		                		CREATE.node(chunk).label("Chunk"),
		                		DO.SET(chunk.property("name")).to(string[1])

		                }); 	
		                JcQueryResult result = dbAccess.execute(query); 
		                
		                String id = string[1];
		     		        		
		        		while(linha.length()!= 0) {
		        			
		        			linha = lerArq.readLine();
					        linha = linha.replaceAll(" ", "");
					        
					        string = linha.split("[(,).]");
					     
					     if(string[0].equals("ck_has_tokens")) {
					    	 
					    	 query.setClauses(new IClause[] { 
				                		
				                		MATCH.node(chunk).label("Chunk").property("name").value(id),
				                		MATCH.node(token).label("Token").property("name").value(string[2]),
				                		CREATE.node(chunk).relation().out().type("ck_has_tokens").node(token)
				                }); 	

						    
					     }else if(string[0].equals("ck_hasHead")) {
					    	 
					    	 query.setClauses(new IClause[] { 
				                		
				                		MATCH.node(chunk).label("Chunk").property("name").value(id),
				                		MATCH.node(token).label("Token").property("name").value(string[2]),
				                		CREATE.node(chunk).relation().out().type("ck_hasHead").node(token)
				                }); 	

					    	 
					    	 
					     }  else if(string.length>2){
					    	 
					    	 query.setClauses(new IClause[] { 
				                		
				                		MATCH.node(chunk).label("Chunk").property("name").value(id),
				                		DO.SET(chunk.property(string[0])).to(string[2])
				                }); 
					     }   
					        
					     result = dbAccess.execute(query); 
					           
					       
		        		}
		        		


			        	
		        	}else if(linha.split("[().]")[0].equals("%st")) {
		        		
			        	if(countSentences==qtdSentences) {

		        			break;
		        			
			        	}
			        	
		        		countSentences++;
		        		
		        	}
		        	
		         }

		        }
		      

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		
	}

	public void CarregarChunkSucc(int qtdSentences) {

		
		 FileReader arq;
		 IDBAccess  dbAccess = ConnectionBD.getConnection();		
	        
		try {
			arq = new FileReader(new File("CorpusACE2004_FULL.pl"));
		     BufferedReader lerArq = new BufferedReader(arq);
		     
		      String linha = lerArq.readLine(); 
		      int countSentences = 1;
		      
		      while (linha != null) {
		    	  
		        linha = lerArq.readLine(); 
		        
		        
		        if(linha!=null) {
		        	
		        	if(linha.split("[\\W]")[0].equals("ck_hasSucc")) {
		        		
		        		JcNode chunk1 = new JcNode("Chunk1"); 

		        		JcNode chunk2 = new JcNode("Chunk2"); 

		                JcQuery query = new JcQuery(); 
		                
		                
		        		while(linha.length()!= 0) {
		        			
						        linha = linha.replaceAll(" ", "");
						        
						        String []string = linha.split("[(,).]");
						     
						     if(string.length>1) {
						    	 
						    		
				        		 query.setClauses(new IClause[] { 
	
					                		MATCH.node(chunk1).label("Chunk").property("name").value(string[1]),
					                		MATCH.node(chunk2).label("Chunk").property("name").value(string[2]),
					                		CREATE.node(chunk1).relation().out().type("ck_hasSucc").node(chunk2)
					                }); 	
	
							    
						     }
						     
						     JcQueryResult result = dbAccess.execute(query);      
	
			        		 linha = lerArq.readLine();
		        		}
		        		
		        		
		        	}else if(linha.split("[().]")[0].equals("%st")) {
		        		if(countSentences==qtdSentences) {

		        			break;
		        			
		        		}
		        		countSentences++;
		        		
		        	}
		        	
		         }

		        }
		      

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		
	}
	
	public void CarregarNextToken(int qtdSentences) {

		FileReader arq;
		 IDBAccess  dbAccess = ConnectionBD.getConnection();		
	        
		try {
			arq = new FileReader(new File("next_ALL_ACE2004.pl"));
		     BufferedReader lerArq = new BufferedReader(arq);
		     
		      String linha = lerArq.readLine(); 
		      int countSentences = 2;
		      
		      while (linha != null) {
		    	  
		    	  
		        linha = lerArq.readLine(); 
		        
		        if(linha!=null) {
		        	
		        	if(linha.split("[\\W]")[0].equals("t_next")) {
		        		System.out.println();
		        		System.out.println(linha);
		        		
		        		JcNode token1 = new JcNode("Token1"); 

		        		JcNode token2 = new JcNode("Token2"); 
		        		
		                JcQuery query = new JcQuery(); 
		                
		        		while(linha.length()!= 0) {
		        			
						     linha = linha.replaceAll(" ", "");
						    
						     String [] string = linha.split("[(,).]");
						     
						     if(string.length>1) {
						    	 System.out.println(linha);
						    	 
						    	 query.setClauses(new IClause[] { 
	
					                		MATCH.node(token1).label("Token").property("name").value(string[1]),
					                		MATCH.node(token2).label("Token").property("name").value(string[2]),
					                		CREATE.node(token1).relation().out().type("t_next").node(token2)
					                }); 	
	
							    
						     }
						     
						     JcQueryResult result = dbAccess.execute(query);      

		        			linha = lerArq.readLine();  
		        		}
		        		


		        	}else if(linha.split(" ")[0].equals("%")) {
		        		if(countSentences==qtdSentences) {

		        			break;
		        			
		        		}
		        		countSentences++;
		        		
		        	}
		        	
		         }

		        }
		      

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void CarregarDependencias(int qtdSentences) {
		
		FileReader arq;
		 IDBAccess  dbAccess = ConnectionBD.getConnection();		
	        
		try {
			arq = new FileReader(new File("dependency_ACE_2004.pl"));
		     BufferedReader lerArq = new BufferedReader(arq);
		     
		      String linha = lerArq.readLine(); 
		      int countSentences = 2;
		      
		      while (linha != null) {
		    	  
		    	  
		        linha = lerArq.readLine(); 
		        
		        if(linha!=null) {
		        	
		        	if(linha.split("[\\W]")[0].equals("t_hasDep")) {
		        		System.out.println();
		        		System.out.println(linha);
		        		
		        		JcNode token1 = new JcNode("Token1"); 

		        		JcNode token2 = new JcNode("Token2"); 

		                JcQuery query = new JcQuery(); 
		        		                
		        		while(linha.length()!= 0) {
		        			
			        		linha = linha.replaceAll(" ", "");
						        
						    String[]string = linha.split("[(,).]");
						     
						     if(string.length>2) {
						    	 System.out.println(linha);
						    	 query.setClauses(new IClause[] { 
	
					                		MATCH.node(token1).label("Token").property("name").value(string[2]),
					                		MATCH.node(token2).label("Token").property("name").value(string[3]),
					                		CREATE.node(token1).relation().out().type(string[1]).node(token2)
					                }); 	
	
						     }
						     
						     JcQueryResult result = dbAccess.execute(query);      

		        			 linha = lerArq.readLine();
					          
		        		}
		        		

		        	}else if(linha.split(" ")[0].equals("%")) {
		        		if(countSentences==qtdSentences) {

		        			break;
		        			
		        		}
		        		countSentences++;
		        		
		        	}
		        	
		         }

		        }
		      

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
