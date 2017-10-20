//
// Created by Wang,Shiyi on 17/9/14.

// 左右   斜前方   都不能坐
// 问最多能做多少人
//

#include <iostream>
using namespace std;

int main ( )
{
    int m, n;
    cin >> m >> n;   // m行n列
    int sum1 = 0, sum2 = 0;
    char **chair = new char*[m];
    for (int i = 0; i < m; i++)
    {
        chair[i] = new char[n];
    }
    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> chair[i][j];     // 输入每个座位上是否有人
        }
    }
    if(n % 2)   // n为偶
    {
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if(!(j % 2) && chair[i][j] == '.')  // 奇数列有人
                    sum1++;
                if( (j % 2) && chair[i][j] == '.')  // 偶数列有人
                    sum2++;
            }
        }
        if(sum1 > sum2)
            cout << sum1 << endl;
        else
            cout << sum2 << endl;
    }
    else    // n为奇
    {
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if(!(j % 2) && chair[i][j] == '.')  // 奇数列有人
                    sum1++;
            }
        }
        cout << sum1 << endl;
    }
    for (int i = 0; i < m; i++)
    {
        delete[] chair[i];
    }
    delete[] chair;
    return 0;
}