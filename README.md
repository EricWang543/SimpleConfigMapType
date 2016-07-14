# SimpleConfigMapType

A sample config file:
--------------------------------------------
"here is parameters #1"
"here is parameters #2"

/# comments can be anywhere out of quotation marks, use / as escape sign in quotation marks
/# you can not use " or @ in comment!
@"this is target #1"
@"this is target #2" @ "this can be target #3"
"this is a source", "this is second source"
-------------------------------------------------

Test result:

Parameter Set = [here is parameters #1, here is parameters #2]
Map Content = 
[this is second source, this is a source]:[this is target #1, this can be target #3, this is target #2]

false