#include <bits/stdc++.h>
int main() {
  int p, d, b; scanf("%d %d %d", &p, &d, &b);
  int pts = p + (2*d) + (3*b);
  if(pts >= 150) printf("B\n");
  else if(pts >= 120) printf("D\n");
  else if(pts >= 100) printf("P\n");
  else printf("N\n");
}