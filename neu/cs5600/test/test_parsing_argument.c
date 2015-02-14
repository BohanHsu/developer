#include <stdio.h>
#include <string.h>


void
main ()
{
  String argument = "foo bar tar";
  void **esp;
  parse_argument (argument, esp);
}

static bool
parse_argument (const char *cmd_line, void **esp)
{
  char* stack_top = *esp;
  char* separator = " ";
  char* current_char;

  char* argu_begin;
  char* argu_end;

  size_t argu_length;

  /* set current_char to the end of command line */
  current_char = cmd_line = strlen(cmd_line);

  while (current_char >= cmd_line)
    {
      /* skip the separator */
      while (current_char >= cmd_line)
        {
          if (strrchr (separator, *current_char) != NULL || *current_char == '\0')
            current_char--;
          else
            break;
        }
      argu_end = current_char + 1;

      /* check argument letter */
      while (curr >= cmd_line)
        {
          if (strrchr (separator, *current_char) == NULL)
            current_char--;
          else
            break;
        }
      argu_begin = curr + 1;

      argu_length = argu_end - argu_begin;

      // this part should be in a separator function
      // check for stack overflow
      if ((int)*esp - (int)stack_top + argu_length + 1 > PGSIZE)
        return false;
      // push argument to stack
      strlcpy (stack_top - argu_length -1, argu_length, argu_length + 1);
      *(stack_top - 1) = '\0';

      /* update stack pointer */
      stack_top -= (argu_length + 1);
    }

  /* round stack top to multiples of 4 */
  char* argv_begin = stack_top;

  int count_limit;

  /* Push word alignment into stack */
  if (((int)stack_top) % 4 == 3)
    count_limit = 1;
  else
    count_limit = 2;

  while (count_limit > 0)
    {
      if ((int)*esp - (int)stack_top + 1 > PGSIZE)
        return false;

      stack_top--;
      *stack_top = 0;

      if (((int)stack_top) % 4 == 0)
        count_limit--;
    }

  char *p PHYS_BASE - 1;
  int argc = 0;
  while ((p >= argv_begin))
    {
      p--;
      while ((p >= argv_begin) && (*p != '\0'))
        {
          p--;
        }

      if (!push_argv (&stack_top, (void *)(p + 1), esp))
        return false;

      argc++;
    }

  if (!push_argv (&stack_top, (void *)(stack_top), esp))
    return false;

  if (!push_argv (&stack_top, (void *)(argc), esp))
    return false;

  if (!push_argv (&stack_top, NULL, esp))
    return false;

  *esp = stack_top;

  return true;
}
