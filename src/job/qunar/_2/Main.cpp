//
// Created by Wang,Shiyi on 17/9/14.

// 网络直径
//

#include <iostream>
#include <map>
#define INT_MAX 0x3f3f3f
using namespace std;
int main()
{
    int p, r;
    cin >> p >> r;
    string n1, n2;
    map<string, int> names;
    int **relation = new int* [p];  // 图的邻接矩阵
    for(int i = 0; i < p; i ++){
        relation[i] = new int [p];
    }
    for(int i = 0; i < p; i ++)
    {
        for(int j = 0; j < p; j++)
        {
            if(i == j) relation[i][j] = 0;
            else relation[i][j] = INT_MAX;      // 不相交
        }
    }
    int k = 0;  // 给每个名字转换为编号
    for(int i = 0; i < r; i ++)     // 构造邻接矩阵
    {
        cin >> n1 >> n2;
        if(names.count(n1) == 0)    // n1未出现过
            names[n1] = k++;
        if(names.count(n2) == 0)    // n2未出现过
            names[n2] = k++;
        relation[names[n1]][names[n2]] = 1;     // 相交
        relation[names[n2]][names[n1]] = 1;
    }
    for(int k = 0; k < p; k ++) // 中间结点
    {
        for(int i = 0; i < p; i ++)
        {
            for(int j = 0; j < p; j ++)     // floyd算法，求得任意两点间的最短距离
            {
               if(relation[i][k] + relation[k][j] < relation[i][j])
                    relation[i][j] = relation[i][k] + relation[k][j];
            }
        }
    }
    int res = 0;
    for(int i = 0; i < p; i ++)
    {
        for(int j = 0; j < p; j ++)
        {
            if(i == j)
            {
                break;
            }
            if(relation[i][j] == INT_MAX)   // 有孤立结点
            {
                cout<<"DISCONNECTED"<<endl;
                return 0;
            }
            if(relation[i][j] > res)  // 距离更大
                res = relation[i][j];

        }
    }
   cout << res << endl;
   for(int i = 0; i < p; i++)
        delete [] relation[i];
   delete [] relation;
   return 0;
}