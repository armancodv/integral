# java.math.numerical.library

Java numerical library is based on numerical methods (https://en.wikipedia.org/wiki/Numerical_method) and it was tested using Matlab/Octave (https://www.gnu.org/software/octave/)

## Java numerical library features:

+ Functions (https://en.wikipedia.org/wiki/Function_(mathematics))

+ Functions with n vars (https://en.wikipedia.org/wiki/Function_of_several_real_variables)
  + Math expression parser: java.math.expression.parser that you can find in my github repository. It is a recursive ascent parser

+ Equations solver (https://en.wikipedia.org/wiki/Equation)
  + Numerical methods to solve equations
    + Dichotomy (https://en.wikipedia.org/wiki/Bisection_method)
    + Newton–Raphson (https://en.wikipedia.org/wiki/Newton%27s_method)

+ System of equation solver (https://en.wikipedia.org/wiki/System_of_linear_equations)

+ Sytems of nonlinear equation solver (https://en.wikipedia.org/wiki/Nonlinear_system)

        String equation1 = "2.35*e^(-3)*(x+y)^1.75-75+z";
        String equation2 = "4.67*e^(-3)*x^1.75+20-z";
        String equation3 = "3.72*e^(-2)*y^1.75+15-z";

+ System of ODE solver (https://en.wikipedia.org/wiki/Ordinary_differential_equation)
  + Numerical methods to solve System of ODE
    + Runge-Kutta (https://en.wikipedia.org/wiki/Runge%E2%80%93Kutta_methods)
    + Predictor-Corrector (https://en.wikipedia.org/wiki/Predictor%E2%80%93corrector_method)
    
+ Cauchy equation (https://en.wikipedia.org/wiki/Cauchy_problem)
  + (https://www.encyclopediaofmath.org/index.php/Cauchy_problem,_numerical_methods_for_ordinary_differential_equations)
  + ODE equation: y'(x)= f(x,y); y(x0) = y0
  + numerical methods to solve the Cauchy Equation ( solution y(x) )
    + Runge-Kutta
    + Adaptative Runge-Kutta
    + Predictor - Corrector   
    
+ Dirichlet equation (https://en.wikipedia.org/wiki/Dirichlet_problem) 
  + ODE equation: a(x)*y'' + b(x)*y' + c(x)*y = f(x)
  + Dirichlet boundary condition: y(a)=A , y(b)=B || A = alpha, B = Beta (https://en.wikipedia.org/wiki/Dirichlet_boundary_condition)  
       
+ Neumann equation
  + ODE equation: a(x)*y'' + b(x)*y' + c(x)*y = f(x)
  + Solutions for Neumann boundary conditions: (https://en.wikipedia.org/wiki/Neumann_boundary_condition)
    +  y'(a)=A   y'(b)=B || A = alpha, B = beta
    +  y'(a)=A   y(b)=B  || A = alpha, B = beta
    +  y(a)=A    y'(b)=B || A = alpha, B = beta
   
+ Vectors:
  + Basic math operations with vectors
  + Eclidean norm & Maximun norm https://en.wikipedia.org/wiki/Norm_(mathematics)
  
+ Matrix
  + Basic math operations with matrix 
  + Linear Algebra (https://en.wikipedia.org/wiki/Linear_algebra)
  + Inverse solver (https://en.wikipedia.org/wiki/Invertible_matrix)
  + Determinant solver (https://en.wikipedia.org/wiki/Determinant)
  + Identity matrix (https://en.wikipedia.org/wiki/Identity_matrix)
  + Balanced Matrix (https://en.wikipedia.org/wiki/Balanced_matrix)
  + Householder matrix (https://en.wikipedia.org/wiki/Householder_transformation)
  + Hessenber matrix (https://en.wikipedia.org/wiki/Hessenberg_matrix)
  + Eigenvalues & eigenvectors calculator (https://en.wikipedia.org/wiki/Eigenvalues_and_eigenvectors)
    + numerical methods to calculate Eigenvalues:
      + Power Iteration (https://en.wikipedia.org/wiki/Power_iteration) 
      + QR Algorithm (https://en.wikipedia.org/wiki/QR_algorithm)
    + numerical method to calculate eigenvectors
      + Inverse iteration https://en.wikipedia.org/wiki/Inverse_iteration

+ Integrals: This library can solve integrals using numerical integration (https://en.wikipedia.org/wiki/Numerical_integration):
  + Integrals: numerical methods to solve integrals (https://en.wikipedia.org/wiki/Integral)
    + Trapezoidal (https://en.wikipedia.org/wiki/Trapezoidal_rule)
    + Simpson (https://en.wikipedia.org/wiki/Simpson%27s_rule)
    + Romberg (https://en.wikipedia.org/wiki/Romberg%27s_method
  + Multidimensional Integrals: (https://en.wikipedia.org/wiki/Multiple_integral)
    + QuasiMontecarlo: (https://en.wikipedia.org/wiki/Quasi-Monte_Carlo_method) numerical method to solve multidimensional integrals
 
+ Derivatives: This library can solve derivatives using numerical diferenciation (https://en.wikipedia.org/wiki/Numerical_differentiation)

  + Derivatives (https://en.wikipedia.org/wiki/Derivative)

  + Partial derivatives (https://en.wikipedia.org/wiki/Partial_derivative)
  
+ Polynomials with real & complex coefficients (https://en.wikipedia.org/wiki/Polynomial)
  + Root or zero of a polynomial solver (https://en.wikipedia.org/wiki/Zero_of_a_function)
    + Laguerre: (https://en.wikipedia.org/wiki/Laguerre%27s_method) numerical method to calculate roots

+ Series solver (https://en.wikipedia.org/wiki/Series_(mathematics))
  + function series: functions with one or n real vars (https://en.wikipedia.org/wiki/Function_series)

+ Complex Numbers (https://en.wikipedia.org/wiki/Complex_number)

+ Combination (https://en.wikipedia.org/wiki/Combination)

+ Factorial (https://en.wikipedia.org/wiki/Factorial)


There are examples in the test folder

Reference Book: Numerical recepies in C (https://en.wikipedia.org/wiki/Numerical_Recipes)

## Professional Services
If you are interested in logical parsers or any task related to parsers, you can consult my professional services page https://github.com/sbesada/professional.services        

## Donation
If you think that my work deserves a donation, you can do it: https://sbesada.github.io/
