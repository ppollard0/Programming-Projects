# Module graph
# Author: S. Sigman    Date Created: 9/1/2022
#
# This module provides graph data structures. The following
# Graph representations are provided:
#    * GraphAM - graph stored  using an adjacency
#                matrix - the matrix is an numpy array
#    * GraphAL - graph stored using an adjacency list -
#                TO BE Implemented (9/1/2022)
#    Methods
#     Unless specified, methods belong to both graph storage classes.
#      * consructor
#          Graph{AM|AL}(self, verts :list[str], edges :list[Edges]=[],
#                          directed :bool = False, eleType :str ='i' ) -> None
#             eleType in {'i','i1','i2','i4','i8','f2','f4','f8'} ie. numpy
#             dtype character code abbreviations
#      * getV(self) -> list[str]
#      * GraphAM.getGraph(self) -> Adj_Mat
#      * GraphAL.getGraph(self) -> Adj_Lst
#      * addEdges(self, edges :list[Edge]) -> None
#      * degree(self, vert :Vertex) -> int
#      * inDegree(self, vert :Vertex) -> int, not defined for undirected graph
#           returns None 
#      * outDegree(self, vert Vertex) -> int, not defined for undirected graph
#           returns None
#
# Data types
#   Edge - tuple representing a edge (str, str)
#   Adj_Mat - adjacency matrix - 2D matrix of equal size
#   Adj_List - adjacency list - to be created 
#   Vertex - type of a vertex 
#
# Modification Log:
#    *  9/02/2022 - Completed documentation. S. Sigman 
#    *  9/15/2022 - Updated documentation and definitions for degree, indegree,
#                   and outdegree methods.  S. Sigman

import numpy as np
from nptyping import NDArray, Shape, Integer
from typing import Any

Vertex = str
Edge = tuple[Vertex,Vertex]
Adj_Mat = NDArray[Shape['Dim, Dim'], Integer]
Adj_Lst = NDArray[Shape['Sze, Sze'], Integer]

class GraphAM:
    def __init__(self, verts: list[Vertex], edges: list[Edge]=[], *, directed :bool =False, eleType :str ='i') -> None:
        self.V :list[str] = verts
        self.directed :bool = directed
        self.graph :Adj_Mat = np.zeros((len(verts),len(verts)), dtype=eleType)
        # add edges if any are specified
        if edges != []:
            for e in edges:
                # get index of start and end vertices
                start = self.V.index(e[0])
                end = self.V.index(e[1])
                # add directed edge
                self.graph[start,end] = 1
                # if undirected add symmetric edge
                if not self.directed:
                    self.graph[end,start] = 1


    # Method to return the set of verticies
    def getV(self) -> list[Vertex]: 
        return(self.V)

    # Method to return the graph object
    def getGraph(self) -> Adj_Mat:
        return(self.graph)

    # Method to add the list of edges to the graph
    def addEdges(self, edges :list[Edge]) -> None: 
        if edges != []:
            for e in edges:
                # get index of start and end vertices
                start = self.V.index(e[0])
                end = self.V.index(e[1])
                # add directed edge
                self.graph[start,end] = 1
                # if undirected add symmetric edge
                if not self.directed:
                    self.graph[end,start] = 1

    # Method to return the degree of an edge.  In a directed
    # graph it returns degree = indegree + outdegree
    def degree(self, vert : Vertex) -> int:
        # stub - add the body
        if not self.directed:
            return len(self.graph[vert])
        else:
            deg = self.inDegree(vert) + self.outDegree(vert)
        return deg 

    # Method to return the in-degree of a node in a directed
    # graph.  In the case of an undirected graph, None is 
    # returned.
    def inDegree(self, vert :Vertex) -> int:
        # stub - add the body
        inDeg = 0 # # of edges pointing to vert
        if (self.directed):
            ver = self.V.indexOf(vert)
            # add all edges in that row 
            inDeg = np.sum(self.V, axis = ver)
            print(inDeg)
            return inDeg
        else:
            return 0

    # Method to return the out-degree of a node in a 
    # directed graph.  In the case of an undirected
    # graph, None is returned.
    def outDegree(self, vert :Vertex) -> int:
        # stub - add the bodyver = self.V.indexOf(vert)
        
        # outDeg = 0 # # edges that vert is pointing to
        if (self.directed):
            # add all edges in that colum 
            
            # ver = self.V.indexOf(vert)
            # outDeg = np.sum(self.V, axis = ver)
            # return outDeg

            return(len(self.graph[vert]))
        else:
            return 0

# TO DO - Create an adjacency list class, GraphAL
class GraphAL:
    def __init__(self, verts: list[Vertex], edges: list[Edge]=[], *, directed :bool =False, eleType :str = 'i') -> None:
        self.V :list[str] = verts
        self.directed :bool = directed
        self.graph :Adj_Lst = np.zeros((len(verts), len(verts)),dtype=eleType)
        if edges != []:
            for e in edges:
                # get index of start and end vertices
                start = self.V.index(e[0])
                end = self.V.index(e[1])
                # add directed edge
                self.graph[start,end] = 1
                # if undirected add symmetric edge
                if not self.directed:
                    self.graph[end,start] = 1
    
    def getV(self) -> list[Vertex]: 
        return(self.V)

    def getList(self) -> Adj_Lst:
        return(self.graph)

