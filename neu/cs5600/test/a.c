#include <stdio.h>
#include <string.h>
main()
{
  printf("%d\n", sizeof(char));
  char *c = &' ';
  printf("%d\n", strrchr (' ', c));
}
