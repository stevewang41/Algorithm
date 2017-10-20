//
// Created by Wang,Shiyi on 17/9/14.
//
// 部分和问题：给定整数a1、a2、.......an，判断是否可以从中选出若干数，使它们的和恰好为K。
//

#include <iostream>
using namespace std;

int n, k;
int a[22];
bool selected[22];

bool dfsSum(int i, int sum)     // 输入当前起点a[i]，当前和为sum，输出是否满足当前和为K
{
    if (i == n)
    {
        return sum == k;
    }
    selected[i] = false;        // 不加入a[i]
    if (dfsSum(i + 1, sum))
    {
        return true;
    }
    selected[i] = true;         // 加入a[i]
    if (dfsSum(i + 1, sum + a[i]))
    {
        return true;
    }
    return false;
}

int main()
{
    while (cin >> n >> k)
    {
        for (int i = 0; i < n; i++)
        {
            cin >> a[i];
        }
        if (dfsSum(0, 0)){
            cout << "YES" << endl;
            for (int i = 0; i < n; i++){
                if (selected[i])    // 按输入顺序依次输出是由哪几个数的和组成
                {
                    cout << a[i] << " ";
                }
            }
            cout << endl;
        }
        else
        {
            cout << "NO" << endl;
        }
    }
    return 0;
}
