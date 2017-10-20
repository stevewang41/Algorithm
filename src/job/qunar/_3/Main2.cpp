//
// Created by Wang,Shiyi on 17/9/16.
//

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
    infect(chair, 0, 0);
    for (int i = 0; i < m; i++)
    {
        delete[] chair[i];
    }
    delete[] chair;
    return 0;
}

int infect(char **chair, int i, int j, bool sit, int sum)     // 输入当前起点a[i]，当前和为sum，输出是否满足当前和为K
{
    if (sit && chair[i][j] == '.') {
        sum++;
        matrix[i][j] = 'x';
        matrix[i-1][j-1] = 'x';
        matrix[i-1][j+1] = 'x';
        matrix[i][j-1] = 'x';
        matrix[i][j+1] = 'x';
        infect(chair, i, j+1, false, sum)
    }
    else
    {
        infect(chair, i, j+1, true, sum)
    }
    return infect(chair, i, j, sum)

}

