
#include <stdio.h>

void copy (char from[], char to[]);

main ()
{
  printf ("hello world\n");
  char c0 = '0';
  char from[10];
  char to[10];

  int i;
  for (i = 0; i < 9; i++)
  {
    from[i] = 'a' + i;
  }
  from[9] = '\n';

  copy (from, to);

  printf ("to array: ");
  for (i = 0; i < 9; i++)
    printf ("%c ", to[i]);

  printf ("\n");

}

void copy (char from[], char to[])
{
  int i = 0;
  while ((to[i] = from[i]) != '\n')
    ++i;
}
