import java.util.Stack;

/**
 * Created by RobertMacBookPro on 11/29/15.
 */
public class UndirectedGraph<T> extends DirectedGraph<T> implements GraphInterface<T>, java.io.Serializable
{
    public UndirectedGraph()
    {
        super();
    }

    public boolean addEdge(T startVertex, T endVertex, double edgeWeight)
    {
        return super.addEdge(startVertex, endVertex, edgeWeight) && super.addEdge(endVertex, startVertex, edgeWeight);
    }

    public boolean addEdge(T startVertex, T endVertex)
    {
        return super.addEdge(startVertex, endVertex) && super.addEdge(endVertex, startVertex);
    }

    public int getNumberOfEdges()
    {
        return super.getNumberOfEdges() / 2;
    }

    public Stack<T> getTopologicalOrger() throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }
}
