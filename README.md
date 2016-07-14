# SimpleConfigMapType

comments can be anywhere out of quotation marks, but you can not use " or @ in comment! 

use / as escape sign in quotation marks

the tester will test whether "this is second source" and "this won't be in the set" exist in the first sets.

A sample config file:
--------------------------------------------
"here is parameters #1"

"here is parameters #2"

comments

comments

comments~~~

@"this is target #1"

@"this is target #2" @ "this can be target #3"


you can even add comments here.

"this is a source", "this is second source"

"there is a example of add /" in the quotation mark"

@"this is another target"

"another source"


Test result:
-------------------------------------------------
Parameter Set = [here is parameters #1, here is parameters #2]

Map Content =
 
[another source]

  -> [this is another target]
  
[this is second source, this is a source, there is a example of add " in the quotation mark]

  -> [this is target #1, this can be target #3, this is target #2]
  

true

false
