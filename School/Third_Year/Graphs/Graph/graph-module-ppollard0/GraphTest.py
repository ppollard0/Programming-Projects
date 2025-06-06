
import numpy as np
import graph
from graph import Adj_Mat, Edge, Adj_Lst

if __name__ == "__main__":
    verts: list[str] = ['a', 'b', 'c']
    edges: list[Edge] = [('a','b'), ('c','a')]

    gm = graph.GraphAM(verts)
    gm.addEdges(edges)
    print('The verticies are ')
    print(*gm.getV(), sep=',')
    print('The empty graph')
    print(gm.getGraph())
    print('The in degree of ' + verts[0] + ' is ' + str(gm.inDegree(verts[0])))
    print('The out degree is of ' + verts[0] + str(gm.outDegree(verts)))
    print('The degree is ' + str(gm.degree(verts)))
    

    gl = graph.GraphAL(verts)
    print('The list')
    print(gl.getList)