#include <bits/stdc++.h>

using namespace std;


int main(){
  int p, d, b;

  scanf("%d %d %d", &p, &d, &b);

  int pontos = p + 2*d + 3*b;

  if (pontos >= 150)
    printf("B\n");
  else if (pontos >= 120)
    printf("D\n");
  else if (pontos >= 100)
    printf("P\n");
  else
    printf("N\n");

  return 0;
}