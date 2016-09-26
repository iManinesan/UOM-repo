/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder;

import java.util.Stack;

/**
 *
 * @author linganesan
 */
public class DFS {
    
     Graph g;
     
     public DFS(Graph g){
        this.g=(Graph)g;
     }
    
    
    public void dfsSearch()
	{
		//DFS uses Stack data structure
		Stack s=new Stack();
		s.push(g.rootNode);
		g.rootNode.visited=true;
		g.printNode(g.rootNode);
		while(!s.isEmpty())
		{
                   
			Node n=(Node)s.peek();
			Node child=g.getUnvisitedChildNode(n);
                         
			if(child!=null)
			{
                            	child.visited=true;
				g.printNode(child);
				s.push(child);
                                if(child.label.equals("25")){
                         break;
                         }
			}
			else
			{
				s.pop();
			}
                        
                        
		}
		//Clear visited property of nodes
		g.clearNodes();
	}
    
}
