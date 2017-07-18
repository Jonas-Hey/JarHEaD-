Param(
	[parameter(Mandatory=$true)]
	[string]$arg1, #argument1
	[string]$arg2 #argument2

)

#sample input:
#arg1=Frank
#arg2=Martin
#will print out "Hello Frank Martin" 

Write-Host "Hello " $arg1 " " $arg2