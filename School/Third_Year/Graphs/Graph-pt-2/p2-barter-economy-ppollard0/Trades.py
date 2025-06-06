# Module Trades
# Author: P. Pollard    Date Created: 10/12/2022
#
# Makes trades that are possible
#
# Methods
#

# Trades of the items PC Rb Di Em IS Rp MP HP TP FR Sh Rs

from http.client import REQUEST_URI_TOO_LONG
from ssl import VERIFY_X509_STRICT
from turtle import pos
import numpy as np
from nptyping import NDArray, Shape, Integer
from typing import Any
import queue

# Tuple setup
# Tuples hold the item being traded, the item received, the amount you need to trade,
# and the amount received

Rp_PC = ('Rp', 'PC', 4, 1)
TP_Di = ('TP', 'Di', 3, 1)
Em_Rs = ('Em', 'Rs', 2, 1)
IS_MP = ('IS', 'MP', 6, 2)
Di_FR = ('Di', 'FR', 6, 1)
Rp_Sh = ('Rp', 'Sh', 1, 1)
Em_IS = ('Em', 'IS', 5, 1)
Em_Di = ('Em', 'Di', 2, 1)
Di_IS = ('Di', 'IS', 3, 1)
Rs_PC = ('Rs', 'PC', 2, 1)
Di_Sh = ('Sh', 'Di', 3, 1)
Sh_Rs = ('Sh', 'Rs', 2, 1)
TP_HP = ('TP', 'HP', 2, 1)
Rb_TP = ('Rb', 'TP', 3, 1)
Rs_Rb = ('Rs', 'Rb', 1, 3)
Sh_Rb = ('Sh', 'Rb', 1, 1)
FR_PC = ('FR', 'PC', 1, 1)
PC_FR = ('PC', 'FR', 1, 1)

#Dictionary setup

TradeDict = {
'Rp_PC' : Rp_PC,     'TP_Di' : TP_Di,     'Em_Rs' : Em_Rs,     'IS_MP' : IS_MP,     'Di_FR' : Di_FR,
'Rp_Sh' : Rp_Sh,     'Em_IS' : Em_IS,     'Em_Di' : Em_Di,     'Di_IS' : Di_IS,     'Rs_PC' : Rs_PC,
'Di_Sh' : Di_Sh,     'Sh_Rs' : Sh_Rs,     'TP_HP' : TP_HP,     'Rb_TP' : Rb_TP,     'Rs_Rb' : Rs_Rb,
'Sh_Rb' : Sh_Rb,     'FR_PC' : FR_PC,     'PC_FR' : PC_FR}

# Trade Setup

# amount of items that the trader posseses 
amount = 0

# Graph Setup

Vertex = str
Edge = tuple[Vertex,Vertex]
Adj_Mat = NDArray[Shape['Dim, Dim'], Integer]
Adj_Lst = NDArray[Shape['Sze, Sze'], Integer]

class GraphAL:
    def __init__(self, verts: list[Vertex], edges: list[Edge]=[], *, directed :bool =True, eleType :str = 'i') -> None:
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

    # Trades items until you arrive at the item that you want
    def Trade(self, have, numOf,  want):
        checked = [] # items already visited
        tradeLst = queue.Queue()
        tradeLst = self.bfs(have, want, numOf, tradeLst, checked)
        statement = 'The trades that you need to do to get from ' + str(numOf) + ' ' + have + ' to at least 1 ' + want + ' is '
        while tradeLst.empty() == False:
            statement = statement + str(tradeLst.get())
            if tradeLst.empty() == False:
                statement = statement + ', '
            else:
                statement = statement + '.'
        print(statement)

        


    # Method to returns how much of an item you have
    def amount():
        return amount

    # Breadth first search to find the item
    def bfs(self, have, want, numOf, tradeLst, checked):
        repeat = False
        numEdges = len(edges)
        visited = []
        count = 0
        que = queue.Queue()
        end = 'end'
        dead = 'dead'
        
        # Check if this is repeat
        chkct = len(checked)
        if chkct != 0:
            for i in checked:
                if i == want:
                    tradeLst.put(dead)
                    return tradeLst
        checked.append(want)

        # Find Trades for item
        while count < numEdges:
            wantedObj = edges[count][1]
            if want == wantedObj:
                visited.append(edges[count][0])
                que.put(edges[count]) # What's put inside of the queue
            count = count + 1

        # check if you can trade for item
        if len(visited) == 0:
            tradeLst.put(dead)
            return tradeLst
        while que.empty() == False:
            item = que.get()
            check = item[0]
            tradeLst.put(check)
            checkNum = TradeDict.get(check + '_' + want)
            i = checkNum[2]
            if check == have and numOf >= i:
                tradeLst.put(end)
                return tradeLst
            else:
                self.bfs(have, check, numOf,tradeLst, checked)
                checked.clear()
        return tradeLst

    # Check the visited items to see if you have the right trade & right amount, 
    # if you do, then return the trade and amount you have remaining.
    
 

if __name__ == "__main__":
    verts: list[str] = ['PC', 'Rb', 'Di', 'Em', 'IS', 'Rp', 'MP', 'HP', 'TP', 'FR', 'Sh', 'Rs']
    edges: list[Edge] = [('Rp', 'PC'), ('TP', 'Di'), ('Em', 'RS'), ('IS', 'MP'), ('Di', 'FR'), 
    ('Rp', 'Sh'), ('Em', 'IS'), ('Em', 'Di'), ('Di', 'IS'), ('Rs', 'PC'), ('Di', 'Sh'), ('Sh', 'Rs'), 
    ('TP', 'HP'), ('Rb', 'TP'), ('Rs', 'Rb'), ('Sh', 'Rb'), ('FR', 'PC'), ('PC', 'FR')]

    Trades = GraphAL(verts)

    Trades.Trade('Rp', 6, 'PC')
    Trades.Trade('Di', 6, 'PC')
    