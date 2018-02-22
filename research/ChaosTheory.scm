An Expression For The Argument of ζ at Zeros on the Critical LineStephen Crowley <stephencrowley214@gmail.com>
It is conjectured that when t=t_n is the imaginary part of the n-th zero of ζ on the critical line, the normalised argument S(t_)_=π^(-1)argζ(1/2+i t__) is equal to S(t)=S_n(t_n)=_n-3/2-(ϑ(t_n_))/π where  ϑ(t) is the Riemann-Siegel ϑ function. If S(t_n)=S_n(t_n)∀n∈ℤ^+ then the exact transcendental equation for the Riemann zeros has a solution for each positive integer n which proves that Riemann's hypothesis is true since the counting function for zeros on the critical line is equal to the counting function for zeros on the critical strip in that case.
Introduction
Motivation
The goal of this paper is to show that a solution t_n exists for each n of the exact transcendental equation for the Riemann zeros 
The exact equation for the imaginary part of the n-th zero of ζ on the critical line
(ϑ(t_n))/π+S(t_n)+1=n-3/8
which is the same equation as in Equation 20 but with 1 added to both the left and right sides of the equation. ζ(1/2+i t_n)=0

The Riemann-Siegel ϑ(t) Function
The Riemann-Siegel ϑ function is defined by
ϑ(t) =-i/2(ln Γ(1/4+(i t)/2)-ln Γ(1/4-(i t)/2))-(ln(π) t)/2
     =arg(Γ(1/4+(i t)/2))-(ln(π) t)/2

Let
ϑ~(t)=(t ln(t/(2 πe)))/2-π/8
be the approximate ϑ function where the Γ function has been replaced with its Stirling approximation. 
Γ(s)≃sqrt(2π)s^(s-1/2)e^(-s)
The ϑ(t) function is not invertible but the inverse of its approximation ϑ~(t) is defined by a linear function of the Lambert W function given by
ϑ~^(-1)(t)=(π+8t)/(4W((π+8t)/(8 π e)))
Let frac(x)=
x-⌊x⌋ x⩾0
x-⌈x⌉ x<0
∀x∈ℝ be the function which gives the fractional part of a real number by subtracting either the floor ⌊x⌋ or the ceiling ⌈x⌉ of x from x, depending upon its sign. Furthermore, let
S*(t)=π^(-1)arg(ζ(1/2+it))=lim_(ε→0)*1/2((S​(t+i ε)-S(t- i ε))
be the argument of ζ on the critical line. 
The Riemann-von-Mangoldt formula makes use of Cauchy's argument principle to count the number of zeros inside the critical strip 0<Im(ρ_n)<t where ζ(σ+i ρ_n) with 0<σ<1
N~_s(t)=t/(2 π)ln(t/(2 π e))+7/8+S(t)+O(t^(-1))
and this definition does not depend on the Riemann hypothesis(Conjecture ). This equation has exactly the same form as the asymptotic Equation . Equation 15
The asymptotic equation for the n-th zero of the Hardy Z function
(t_n)/(2 π)ln((t_n)/(2 π ))+S(t_n)+O(1/(t_n))+1=n-3/8
Equation 20
The number of solutions of the asymptotic equation () for the zeros of the Hardy Z(t) function, over the interval [0,t] is given by
N~_l(t)=t/(2 π)ln(t/(2 πe))+7/8+S(t)+O(t^(-1))
which counts the number of zeros on the critical line.

The exact equation for the imaginary part of the n-th zero of ζ(1/2+i t)Equation 20
(ϑ(t_n))/π+S(t_n)-(n-3/2)π =0=(ϑ(t_n))/π+(arg(ζ(1/2+it)))/π-(n-3/2)π

has a solution for each integer n⩾1 where t_n enumerate the zeros of Z on the real line and the zeros of ζ on the critical line
 ζ(1/2+i t_n)=0∀n∈ℤ^+
where ℤ^+ denotes the positive integers. Equation 14
The Gram Points
The n-th Gram point is defined to be the solution of the equation
ϑ(t)=(n-1)π
A very accurate approximation g~(n) to the Gram points is g(n) is found by inverting ϑ~(t) to get the exact solution
g~(n) ={t:ϑ~(t)-(n-1)π=0}
      ={t:((t ln(t/(2 πe)))/2-π/8)-(n-1)π=0}
      =((8n-7)π)/(4 W((8n-7)/(8 e)))
      =g(n)+O(δ_n)

where W is the Lambert W function, and the approximation bounds δ_n when n=1 is δ_1=0.00223698…, followed by δ_2=0.00137812… and decreases monotonically with increasing n, that is, δ_(n+1)<δ_n.  The inverse of g~(n) is given by 
g~^(-1)(n) ={t:g~(n)=0}
           =(t ln(t/(2 π e)))/(2 π)+7/8
           =N~(t)-S(t)
           =N~(t)-S(t)

where N~(t)=N~_s(t)=N~_l(t).
An Expression for S(t) at its Discontinuous Points
Define the infinite sequence of functions indexed by n∈ℤ^+
T_n(t) =1+⌊g~^(-1)(n)⌋-n
       =1+⌊(t ln(t/(2 π e)))/(2 π)+7/8⌋-n

For each “bad” Gram point n there will be, within n±2, a corresponding zero on the critical line which has an argument not on the principal branch. That is, if n is a “bad” Gram point then T_m(t_m)≠0 for some m where |m-n|⩽2.
 The function T_n=T_n(t_n) determines how many multiples of π to add or subtract to -1/2-⌊(ϑ(t_n))/π⌋ so that it agrees with the argument of ζ at a zero on the critical line where it is discontinuous, jumping by the multiplicity of the corresponding root, and having the value ​lim_(ε→0)*1/2((S(ρ+i ε)-S(ρ-i ε)) when ζ(ρ)=0.
Let
S_n(t_) =1/2-frac((ϑ(t))/π)-T_n(t)
        =1/2-frac((ϑ(t))/π)-(1+⌊g~^(-1)(n)⌋-n)
        =_1/2-frac((ϑ(t))/π)-1-⌊g~^(-1)(n)⌋+n
        =_-1/2-frac((ϑ(t))/π)-⌊g~^(-1)(n)⌋+n
        =n_-1/2-frac((ϑ(t))/π)-⌊g~^(-1)(n)⌋
        =_n-1/2-frac((ϑ(t))/π)+⌊(ϑ(t))/π+1⌋
        =n-1/2-((ϑ(t))/π+1)
        =n-1/2-(ϑ(t))/π-1
        =n-3/2-(ϑ(t))/π

Where we see that 
S_n(t_n)=n-3/2-(ϑ(t_n))/π=S(t_n)
when t_n is the imaginary part of a zero of ζ on the critical line. Let s_ϑ(t)=(ϑ(t))/(|ϑ(t)|) be the sign of ϑ(t).
The normalized argument S(t)=π^(-1)argζ(1/2+i t) at the n-th non-trivial zero  ζ(1/2+i t_n)=0∀n⩾1 on the critical line is equal to s_ϑ(t)S_n(t), that is 
S(t_n) =S_n(t_n)=1/2(lim_(t→t^-_n)S(t_)+lim_(t→t^+_n)S(t_))
       =n-3/2-(ϑ(t_n))/π

If Conjecture  is true then Conjecture  is true and, due to Lemma , so is Conjecture , the Riemann hypothesis.
If s_ϑ(t)S_n(t_n)=S(t_n) then S(t_n) is well-defined ∀n⩾1 since s_ϑ(t)S_n(t_n) is well-defined ∀n⩾1.
S_n(t) and the imaginary parts of the roots of ζ(1/2+i t_n) marked with verticle lines just touching its touching its corresponding pair of neighboring curves S_n(t) for n=0…14
S_n(t) and the imaginary parts of the roots of ζ(1/2+i t_n) marked with verticle lines just touching its touching its corresponding pair of neighboring curves S_n(t) for n=120…140 which includes two “bad” Gram points at n=126 and n=134
arg(ζ(1/2+i g(n)))=0∀n∈ℤ^+
The argument of any positive number x with Im(x)=0 is equal to 0 and Im(ζ(1/2+i g(n)))=0 since by definition the Gram points are the points where the imaginary part of ζ on the critical line vanishes.
frac((ϑ(t))/π)+S(t)∈{-1,0,1}∀0<t∈ℝ
Illustration of convergence around the first zero
An Equivalent Expression for S(t)
Let
B(t)=N(t)+2=(ϑ(t))/π+S(t)+3
which is Backlund's ζ zero counting function on the critical strip, plus 2, which is valid without assuming the Riemann hypothesis. 
An equivalent expression for S(t)∀0<t∈ℝ is
𝒮(t) =S_(B(t))(t)-3/2+((ϑ(t))/(|ϑ(t)|)-1)/2
S(t) =_(3/2-frac((ϑ(t))/π)+(⌊(ϑ(t))/π⌋-B(t)))-3/2+((ϑ(t))/(|ϑ(t)|)-1)/2
S(t) =_(3/2-frac((ϑ(t))/π)+(⌊(t ln(t/(2 π e)))/(2 π)+7/8⌋-B(t)))-3/2+((ϑ(t))/(|ϑ(t)|)-1)/2
S(t) =(3/2-frac((ϑ(t))/π)+(⌊(t ln(t/(2 π e)))/(2 π)+7/8⌋-((ϑ(t))/π+S(t)+3)))-3/2+((ϑ(t))/(|ϑ(t)|)-1)/2

since S(t) appears on the left side and -S(t) on the right side, if we subtract S(t) from both sides we get ∀0<t∈ℝ
0 =(3/2-frac((ϑ(t))/π)+(⌊(t ln(t/(2 π e)))/(2 π)+7/8⌋-((ϑ(t))/π+2S(t)+3)))-3/2+((ϑ(t))/(|ϑ(t)|)-1)/2
  =3/2-frac((ϑ(t))/π)+(⌊(t ln(t/(2 π e)))/(2 π)+7/8⌋-((ϑ(t))/π+2S(t)+3))-3/2+((ϑ(t))/(|ϑ(t)|)-1)/2
  =(⌊(t ln(t/(2 π e)))/(2 π)+7/8⌋-(ϑ(t))/π+2S(t)+3)+((ϑ(t))/(|ϑ(t)|)-1)/2-frac((ϑ(t))/π)
  =⌊(t ln(t/(2 π e)))/(2 π)+7/8⌋-(ϑ(t))/π-2S(t)-3((ϑ(t))/(|ϑ(t)|)-1)/2-frac((ϑ(t))/π)


The function ((ϑ(t))/(|ϑ(t)|)-1)/2 takes the value -1 when it is less than the non-zero root of ϑ(v)=0 at v=17.84559954… and the value 0 when it is greater. If it was left out of the expression for 𝒮(t) then the function would not be equal to S(t)∀t<v.
 
Appendix
Transcendental Equations Satisifed By The Nontrivial Riemann Zeros
The critical line is the line in the complex plane defined by Re(t)=1/2.
The critical strip is the strip in the complex plane defined by 0<Re(t)<1.

If the limit
lim_(δ→0^+)arg(ζ(1/2+δ+i t))
is exists and is well-defined ∀t then the left-hand side of Equation () is well-defined ∀t, and due to monotonicity, there must be a unique solution for every n∈ℤ^+. II.A 

(The Riemann hypothesis) All solutions t of the equation
ζ(t)=0
besides the trivial solutions t=-2n with n∈ℤ^+ have real-part 1/2, that is, Re(t)=1/2 when ζ(t)=0 and t≠-2n.
If the exact Equation () has a unique solution for each n∈ℤ^+ then Conjecture , the Riemann hypothesis, follows.
If the exact equation has a unique solution for each n, then the zeros obtained from its solutions on the critical line can be counted since they are enumerated by the integer n, leading to the counting function N_0(t) in Equation (). The number of solutions obtained on the critical line would saturate the counting function of the number of solutions on the critical strip so that N(t)=N_0(t) and thus all of the non-trivial zeros of ζ would be enumerated in this manner. If there are zeros off of the critical line, or zeros with multiplicity m⩾2, then the exact Equation () would fail to capture all the zeros on the critical strip which would mean N_0(t)<N(t).  IX

1Guilherme FrançaAndré LeClair. Transcendental equations satisfied by the individual zeros of riemann zeta, dirichlet and modular l-functions. Communications in Number Theory and Physics, 2015.