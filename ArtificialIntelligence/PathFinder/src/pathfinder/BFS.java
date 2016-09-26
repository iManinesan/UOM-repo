/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author linganesan
 */
public class BFS {
    
     Graph g;
     
     
     BFS(Graph g){
        this.g=(Graph)g;
     }
    
    public void bfsSearch()
	{
		
		//BFS uses Queue data structure
               
		Queue q=new LinkedList();
		q.add(g.rootNode);
		g.printNode(g.rootNode);
		g.rootNode.visited=true;
		while(!q.isEmpty())
		{
			Node n=(Node)q.remove();
			Node child=null;
			while((child=g.getUnvisitedChildNode(n))!=null)
			{
				child.visited=true;
				g.printNode(child);
				q.add(child);
			}
		}
		//Clear visited property of nodes
		g.clearNodes();
	}
    
    

    
}
