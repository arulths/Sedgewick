//page 576+577

import java.io.*;

public class DirectedCycle
{
	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;
	private boolean[] onStack;
	
	public DirectedCycle(Digraph g)
	{
		marked=new boolean[g.V()];
		edgeTo=new int[g.V()];
		onStack=new boolean[g.V()];
		for(int i=0;i<g.V();i++)
			if(!marked[i]) dfs(g,i);
	}
	
	private void dfs(Digraph g,int s)
	{
		marked[s]=true;
		onStack[s]=true;
		for(int i:g.adj(s))
			if(hasCycle()) return;
			else 
				if(!marked[i])
				{
					edgeTo[i]=s;
					dfs(g,i);
				}
				else if(onStack[i])
				{
					cycle=new Stack<Integer>();
					for(int j=s;j!=i;j=edgeTo[j])
						cycle.push(j);
					cycle.push(i);
				}
		onStack[s]=false;
	}
	
	public boolean hasCycle()
	{
		return cycle!=null;
	}
	
	public Iterable<Integer> cycle()
	{
		return cycle;
	}
	
	public static void main(String[] args)
	{
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		String fileName=args[0];
		Digraph g=new Digraph(fileName);
		DirectedCycle dc=new DirectedCycle(g);
		
		if(!dc.hasCycle()) output.println("doesn't have a cycle");
		else
		{
			for(int i:dc.cycle()) output.print(i+" ");
			output.println();
		}
			
	}
}