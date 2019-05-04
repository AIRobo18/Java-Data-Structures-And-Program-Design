import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by RobertMacBookPro on 11/29/15.
 */
public class UndirectedGraph<T> extends DirectedGraph<T> implements GraphInterface<T>, java.io.Serializable, ConnectedGraphInterface<T>
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

    public boolean isConnected(T origin)
    {
        boolean connected = false;
        int numberOfEdges = super.getNumberOfVertices();
        Queue<T> queue = super.getBreadthFirstTraversal(origin);
        if (numberOfEdges == queue.size())
        {
            connected = true;
        }

        return connected;
    }
}
