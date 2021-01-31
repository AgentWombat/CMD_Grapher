import math

def main():

	array = functionToArray(lambda a: 1/a, 0.1, 2, 130, 30)
	tl = True
	while tl == True:

		for row in array:
			for column in row:
				print(column, end = '')
			print()
		tl = False



def f(x):
	return polynomialEval(x, 0, 0, 1)


#Given an input 'x', it evalueates a polynomial with coeficients as specified by all successive function arguments at the input.
# Ex 'polynomialEval(2,1,2,3,4)' => f(x) = 1 + 2x + 3x^2 + 4x^3 evaluated at x = 2.
def polynomialEval(x, *coeficients):
	total = coeficients[0]

	for k in range(1, len(coeficients)):

		total += coeficients[k] * math.pow(x, k)

	return total


# Takes a function 'f', starting x-value 'a' and ending value 'b', and arguments 'xDim' and 'yDim' which specify the arrays dimensions; and then returns an array
# which visualizes a function
def functionToArray(f, a, b, xDim, yDim):
	
	N = int((b-a)*10)
	#This initializes an array with yDim rows and xDim columns
	array = [[" " for k in range(xDim)] for k in range(yDim)] 

	fMin = findMin(f, a, b, N)

	fMax = findMax(f, a, b, N)

	deltaX = (b-a)/(xDim)

	for k in range(0, xDim):

		output = f(a + k*deltaX) 

		mappedOutput = int(map(output, fMin, fMax, 0, yDim))

		array[yDim - mappedOutput - 1][k] = "#"

	return array



def map(val, lowInit, highInit, lowFinal, highFinal):
	
	valFinal = (val - lowInit) * (highFinal - lowFinal)/(highInit - lowInit) + lowFinal

	return valFinal 

def findMin(f, a, b, n):

	deltaX = (b-a)/n

	min = f(a)

	for i in range(1, n+1):

		min = f(a + i*deltaX) if f(a + i*deltaX) < min else min

	return min

def findMax(f, a, b, n):

	deltaX = (b-a)/n

	max = f(a)

	for i in range(1, n+1):

		max = f(a + i*deltaX) if f(a + i*deltaX) > max else max

	return max

if __name__ == '__main__':
	main()


