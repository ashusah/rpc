# rpc
To start application enter spring boot run command - "mvn spring-boot:run"
Debug - mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"

**Requirements**
The calculator has a stack that can contain real numbers.

• The calculator waits for user input and expects to receive strings containing whitespace separated lists of numbers •
  The calculator waits for user input and expects to receive strings containing whitespace separated lists of numbers • The calculator waits for user input and expects to receive strings containing whitespace separated lists of numbers • The calculator waits for user input and expects to receive strings containing whitespace separated lists of numbers • The calculator waits for user input and expects to receive strings containing whitespace separated lists of numbers.

• Numbers are pushed on to the stack. Operators operate numbers that.

• Available operators are +, -, *, /, sqrt, undo, clear.

• The ‘clear’ operator removes all items from the stack.

• The ‘undo’ operator undoes the previous operation. The ‘undo’ operator undoes the previous operation.

• sqrt performs a square root on the top item from stack.

• The ‘+’, ‘ -’, ‘*’, ‘/’ operators perform addition, subtraction, multiplication and
  division respectively on the top two items. 
  
• After processing an input string, the calculator displays current contents of stack as a space • After processing an
  input string, the calculator displays current contents of stack as a space.
 
• Numbers should be stored on the stack to at least 15 decimal places of precision,
  but displayed 10 (or less if it causes no loss of precision).
  
• All numbers should be formatted as plain decimal strings.(no engineering formatting).

• If an operator cannot find a sufficient number of parameters on the stack, warning is displayed: • If an operator cannot find a sufficient number of parameters on the stack, warning is displayed: • If an operator cannot find a sufficient number of parameters on the stack, warning is displayed: • If an operator cannot find a sufficient number of parameters on the stack, warning is displayed: • If an operator cannot find a sufficient number of parameters on the stack, warning is displayed:
  operator <operator> (position: pos>): insufficient parameters
  
• After displaying the warning, all further processing of string terminates and current state of stack is displayed
 
