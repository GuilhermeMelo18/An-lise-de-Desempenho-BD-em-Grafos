### Consulta 01 ####
##PL
cit_res(A,B),t_next(B,C),t_orth(C,lowercase),t_ck_ot(C,np),t_isHeadNP(A),t_gpos(A,nn),t_length(C,4),t_next(C,A)
##Neo4J
MATCH (b:Token)-[:t_next]->(c:Token)-[:t_next]->(a:Token)
WHERE c.t_orth = "lowercase" AND c.t_ck_ot="np" AND c.t_length="4" AND a.t_isHeadNP= a.name AND a.t_gpos= "nn"  
RETURN a,b
####################################

### Consulta 02 ####
##PL
cit_res(A,B),t_next(A,C),t_next(C,B),ck_has_tokens(D,C),t_length(B,4),t_isHeadNP(A),t_length(C,5),t_next(B,E),t_gpos(E,nn)
##Neo4J
MATCH (d:Chunk)-[:ck_has_tokens]->(c)
MATCH(a:Token)-[:t_next]->(c:Token)-[:t_next]->(b:Token)-[:t_next]->(e:Token)
WHERE b.t_length = "4" AND  c.t_length="5" AND a.t_isHeadNP= a.name AND e.t_gpos= "nn"  
RETURN a,b
####################################


### Consulta 03 ####
##PL
employ_staff(A,B),t_ck_tag_ot(A,b-np),ck_has_tokens(C,A),ck_hasSucc(C,D),ck_has_tokens(D,B),t_orth(A,mixedcase),t_pos(B,nnp)
##Neo4J
MATCH (c:Chunk)-[:ck_has_tokens]->(a:Token)
MATCH (c)-[:ck_hasSucc]->(d:Chunk)-[:ck_has_tokens]->(b:Token)
WHERE a.t_ck_tag_ot="b-np" AND a.t_orth= "mixedcase" AND b.t_pos="nnp"
RETURN a,b
####################################


### Consulta 04 ####
##PL
employ_staff(A,B),t_next(B,C),t_orth(C,lowercase),t_ck_ot(C,np),t_orth(A,mixedcase),t_next(C,A)
##Neo4J
MATCH (b:Token)-[:t_next]->(c:Token)-[:t_next]->(a:Token)
WHERE c.t_orth="lowercase" AND c.t_ck_ot= "np" AND a.t_orth="mixedcase"
RETURN a,b
####################################

### Consulta 05 ####
##PL
family(A,B),ck_has_tokens(C,A),ck_hasSucc(C,D),ck_hasType(D,np),t_next(A,E),t_next(E,F),ck_has_tokens(D,F),
t_next(B,G),t_bigPosBef(G,nn-nnp),t_orth(E,lowercase),t_ck_ot(E,np),t_next(G,H),t_length(H,5)
##Neo4J
MATCH (c:Chunk)-[:ck_has_tokens]->(a:Token)-[:t_next]->(e:Token)-[:t_next]->(f:Token)
MATCH(c)-[:ck_hasSucc]->(d:Chunk)-[:ck_has_tokens]->(f)
MATCH(b:Token)-[:t_next]->(g:Token)-[:t_next]->(h:Token)
WHERE d.ck_hasType="np" AND g.t_bigPosBef="nn-nnp" AND e.t_orth="lowercase" AND e.t_ck_ot="np" AND h.t_length="5"
RETURN a,b
####################################

### Consulta 06 ####
##PL
member_g(A,B),t_next(B,C),t_ck_ot(C,np),t_next(A,D),t_ck_tag_ot(D,b-np),t_gpos(B,nn),ck_has_tokens(E,B),t_length(D,3),ck_has_tokens(E,D)
##Neo4J
MATCH (e:Chunk)-[:ck_has_tokens]->(b:Token)-[:t_next]->(c:Token)
MATCH (e)-[:ck_has_tokens]->(d:Token)
MATCH (a:Token)-[:t_next]->(d)
WHERE c.t_ck_ot="np" AND d.t_ck_tag_ot= "b-np" AND b.t_gpos="nn" AND d.t_length="3"
RETURN a,b
####################################

### Consulta 07 ####
##PL
family(A,B),t_ck_tag_ot(A,b-np),t_next(A,C),t_next(C,B),t_length(B,2)
##Neo4J
MATCH (a:Token)-[:t_next]->(c:Token)-[:t_next]->(b:Token)
WHERE a.t_ck_tag_ot="b-np" AND b.t_length= "2"
RETURN a,b
####################################

### Consulta 08 ####
##PL
subsidiary(A,B),t_next(B,C),t_orth(C,lowercase),t_ck_ot(C,np),ck_has_tokens(D,A),t_pos(A,nnp),ck_has_tokens(E,B),ck_hasSucc(E,D)
##Neo4J
MATCH (e:Chunk)-[:ck_has_tokens]->(b:Token)-[:t_next]->(c:Token)
MATCH (e)-[:ck_hasSucc]->(d:Chunk)-[:ck_has_tokens]->(a:Token)
WHERE c.t_orth="lowercase" AND c.t_ck_ot="np" AND a.t_pos="nnp"
RETURN a,b
####################################

### Consulta 09 ####
##PL
part_w(A,B),t_hasDep(appos,A,B),t_next(A,B)
##Neo4J
MATCH(a:Token)-[:appos]->(b:Token) 
MATCH(a:Token)-[:t_next]->(b:Token) 
RETURN a,b
####################################

### Consulta 10 ####
##PL
family(A,B),ck_has_tokens(C,A),ck_hasSucc(C,D),ck_hasType(D,np),t_next(A,E),t_next(E,F),ck_has_tokens(D,F),
t_next(B,G),t_bigPosBef(G,nn-nnp),t_orth(E,lowercase),t_ck_ot(E,np),t_next(G,H)
##Neo4J
MATCH (c:Chunk)-[:ck_has_tokens]->(a:Token)-[:t_next]->(e:Token)-[:t_next]->(f:Token)
MATCH(c)-[:ck_hasSucc]->(d:Chunk)-[:ck_has_tokens]->(f)
MATCH(b:Token)-[:t_next]->(g:Token)-[:t_next]->(h:Token)
WHERE d.ck_hasType="np" AND g.t_bigPosBef="nn-nnp" AND e.t_orth="lowercase" AND e.t_ck_ot="np"
RETURN a,b
####################################
