/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder;



/**
 *
 * @author linganesan
 */
public class PathFinder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
		
		//Lets create nodes as given as an example in the article
		Node[][] grid= new Node[5][5];
                int k=1;
                 for(int i=0;i<5;i++){
                     for(int j=0;j<5;j++){
                         String temp=Integer.toString(k++);
                         grid[i][j]=new Node(temp);
                     }
                 }
                 
                
              //Create the graph, add nodes, create edges between nodes
		Graph g=new Graph();
		for(int i=0;i<5;i++){
                     for(int j=0;j<5;j++){
                           g.addNode(grid[i][j]);
                     }
                }
                 
                //add 2 as the starting point of the graph
		g.setRootNode(grid[0][1]);
		
                for(int i=0;i<5;i++){
                     for(int j=0;j<5;j++){
                         
                        if(i-1>=0)
                        g.connectNode(grid[i][j],grid[i-1][j]);
                        
                        if(i+1<5)
                        g.connectNode(grid[i][j],grid[i+1][j]);
                        
                        if(j-1>=0)
                        g.connectNode(grid[i][j],grid[i][j-1]);
                        
                        if(j+1<5)
                        g.connectNode(grid[i][j],grid[i][j+1]);
                     }
                  }
                
		               
                //Run the output
                System.out.println("*************************************************************************************");
               
                System.out.println();
                 
                System.out.println("CS3612 Intelligent System ");
                System.out.println("Assignment-1");
                
                System.out.println();
                
                
                //Print the Grid
                for(int i=0;i<5;++i){
                    for(int j=0;j<5;++j){
                        if(grid[i][j]!=null)System.out.printf("%-3d ", Integer.parseInt(grid[i][j].label));
                 
                     }
                    System.out.println();
                }
                System.out.println();
                
               
               
               //Run DFS algorithm 
	               
                long lStartTime = System.currentTimeMillis();
		System.out.println("******** DFS Search Algorithm ********");
		DFS dfs = new DFS(g);
                dfs.dfsSearch();
		System.out.println();
                long lEndTime = System.currentTimeMillis();
                long difference = lEndTime - lStartTime;
                System.out.println("Elapsed milliseconds taken for DFS: " + difference);     
                System.out.println();   
                 
                      
                //Run BFS algorithm
                
                lStartTime = System.currentTimeMillis();
		System.out.println("\n******** BFS Search Algorithm ********");
		BFS bfs = new BFS(g);
                bfs.bfsSearch();
                lEndTime = System.currentTimeMillis();
                difference = lEndTime - lStartTime;
                System.out.println();
                System.out.println("Elapsed milliseconds taken for BFS: " + difference); 
                       
                //Run A Star Algorithm 
                
                System.out.println("\n******** A* Algorithm ******** ");
                AStar astar =new AStar();
                astar.test(5, 5, 0, 1, 4, 4); 
                System.out.println();
              
                System.out.println();
                System.out.println("*************************************************************************************");
		
		
    }
    
}
