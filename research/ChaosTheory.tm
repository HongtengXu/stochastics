<TeXmacs|1.99.5>

<style|article>

<\body>
  <doc-data|<doc-title|An Expression For The Argument of <math|\<zeta\>> at
  Zeros on the Critical Line>|<doc-author|<\author-data|<author-name|Stephen
  Crowley \<less\>stephencrowley214@gmail.com\<gtr\>>>
    \;
  </author-data>>>

  <abstract-data|<abstract|It is conjectured that when <math|t=t<rsub|n>> is
  the imaginary part of the <math|n>-th zero of <math|\<zeta\>> on the
  critical line, the normalised argument <math|S<around*|(|t<rsub|>|)><rsub|>=\<pi\><rsup|-1>arg\<zeta\><around*|(|<frac|1|2>+i
  t<rsub|><rsub|>|)>> is equal to <math|S<around*|(|t|)>=S<rsub|n><around*|(|t<rsub|n>|)>=<rsub|>n-<frac|3|2>-<frac|\<vartheta\><around*|(|t<rsub|n><rsub|>|)>|\<pi\>>>
  where \ <math|\<vartheta\><around*|(|t|)>> is the Riemann-Siegel
  <math|\<vartheta\>> function. If <math|S<around*|(|t<rsub|n>|)>=S<rsub|n><around*|(|t<rsub|n>|)>\<forall\>n\<in\>\<bbb-Z\><rsup|+>>
  then the exact transcendental equation for the Riemann zeros has a solution
  for each positive integer <math|n> which proves that Riemann's hypothesis
  is true since the counting function for zeros on the critical line is equal
  to the counting function for zeros on the critical strip in that case.>>

  <section|Introduction>

  <subsection|Motivation>

  The goal of this paper is to show that a solution <math|t<rsub|n>> exists
  for each <math|n> of the exact transcendental equation for the Riemann
  zeros\ 

  <\definition>
    The <verbatim|exact equation> for the imaginary part of the <math|n>-th
    zero of <math|\<zeta\>> on the critical line

    <\equation>
      <frac|\<vartheta\><around*|(|t<rsub|n>|)>|\<pi\>>+S<around*|(|t<rsub|n>|)>+1=n-<frac|3|8>
    </equation>
  </definition>

  which is the same equation as in <cite-detail|z0t|Equation 20> but with 1
  added to both the left and right sides of the equation.
  <math|\<zeta\><around*|(|<frac|1|2>+i t<rsub|n>|)>=0>

  \;

  <subsubsection|The Riemann-Siegel <math|\<vartheta\><around*|(|t|)>>
  Function>

  The Riemann-Siegel <math|\<vartheta\>> function is defined by

  <\equation>
    <tabular*|<tformat|<cwith|1|-1|2|2|cell-halign|l>|<table|<row|<cell|\<vartheta\><around*|(|t|)>>|<cell|=-<frac|i|2><around*|(|ln
    \<Gamma\><around*|(|<frac|1|4>+<frac|i t|2>|)>-ln
    \<Gamma\><around*|(|<frac|1|4>-<frac|i
    t|2>|)>|)>-<frac|ln<around*|(|\<pi\>|)>
    t|2><text|>>>|<row|<cell|>|<cell|=arg<around*|(|\<Gamma\><around*|(|<frac|1|4>+<frac|i
    t|2>|)>|)>-<frac|ln<around*|(|\<pi\>|)> t|2>>>>>>
  </equation>

  Let

  <\equation*>
    <wide|\<vartheta\>|~><around*|(|t|)>=<frac|t ln<around*|(|<frac|t|2
    \<pi\>e>|)>|2>-<frac|\<pi\>|8>
  </equation*>

  be the approximate <math|\<vartheta\>> function where the <math|\<Gamma\>>
  function has been replaced with its Stirling approximation.\ 

  <\equation>
    \<Gamma\><around*|(|s|)>\<simeq\><sqrt|2\<pi\>>s<rsup|s-<frac|1|2>>e<rsup|-s>
  </equation>

  The <math|\<vartheta\><around*|(|t|)>> function is not invertible but the
  inverse of its approximation <math|<wide|\<vartheta\>|~><around*|(|t|)>> is
  defined by a linear function of the Lambert W function given by

  <\equation>
    <wide|\<vartheta\>|~><rsup|-1><around*|(|t|)>=<frac|\<pi\>+8t|4W<around*|(|<frac|\<pi\>+8t|8
    \<pi\> e>|)>>
  </equation>

  Let <math|frac<around*|(|x|)>=<choice|<tformat|<table|<row|<cell|x-\<lfloor\>x\<rfloor\>>|<cell|x\<geqslant\>0>>|<row|<cell|x-\<lceil\>x\<rceil\>>|<cell|x\<less\>0>>>>>\<forall\>x\<in\>\<bbb-R\>>
  be the function which gives the fractional part of a real number by
  subtracting either the floor <math|\<lfloor\>x\<rfloor\>> or the ceiling
  <math|\<lceil\>x\<rceil\>> of <math|x> from <math|x>, depending upon its
  sign. Furthermore, let

  <\equation>
    S*<around*|(|t|)>=\<pi\><rsup|-1>arg<around*|(|\<zeta\><around*|(|<frac|1|2>+it|)>|)>=lim<rsub|\<varepsilon\>\<rightarrow\>0>*<frac|1|2><around*|(|<around*|(|S<around*|\<nobracket\>||(>t+i
    \<varepsilon\>|)>-S<around*|(|t- i \<varepsilon\>|)>|)>
  </equation>

  be the argument of <math|\<zeta\>> on the critical line.\ 

  <\definition>
    The Riemann-von-Mangoldt formula makes use of Cauchy's argument principle
    to count the number of zeros <verbatim|inside the critical strip>
    <math|0\<less\>Im<around*|(|\<rho\><rsub|n>|)>\<less\>t> where
    <math|\<zeta\><around*|(|\<sigma\>+i \<rho\><rsub|n>|)>> with
    <math|0\<less\>\<sigma\>\<less\>1>

    <\equation>
      <wide|N|~><rsub|s><around*|(|t|)>=<frac|t|2
      \<pi\>>ln<around*|(|<frac|t|2 \<pi\>
      e>|)>+<frac|7|8>+S<around*|(|t|)>+O<around*|(|t<rsup|-1>|)>
    </equation>

    and this definition does not depend on the Riemann hypothesis(Conjecture
    <reference|RH>). This equation has exactly the same form as the
    asymptotic Equation <reference|ae>. <cite-detail|z0t|Equation 15>
  </definition>

  <\definition>
    The <verbatim|asymptotic equation> for the <math|n>-th zero of the Hardy
    <math|Z> function

    <\equation>
      <frac|t<rsub|n>|2 \<pi\>>ln<around*|(|<frac|t<rsub|n>|2 \<pi\>
      >|)>+S<around*|(|t<rsub|n>|)>+O<around*|(|<frac|1|t<rsub|n>>|)>+1=n-<frac|3|8><label|ae>
    </equation>

    <cite-detail|z0t|Equation 20>
  </definition>

  <\corollary>
    The number of solutions of the asymptotic equation (<reference|ae>) for
    the zeros of the Hardy <math|Z<around*|(|t|)>> function, over the
    interval <math|<around*|[|0,t|]>> is given by

    <\equation>
      <wide|N|~><rsub|l><around*|(|t|)>=<frac|t|2
      \<pi\>>ln<around*|(|<frac|t|2 \<pi\>e>|)>+<frac|7|8>+S<around*|(|t|)>+O<around*|(|t<rsup|-1>|)><label|N0>
    </equation>
  </corollary>

  which counts the number of zeros <verbatim|on the critical line>.

  \;

  <\conjecture>
    <label|ec>The <verbatim|exact equation> for the imaginary part of the
    <math|n>-th zero of <math|\<zeta\><around*|(|<frac|1|2>+i
    t|)>><cite-detail|z0t|Equation 20>

    <\equation>
      <tabular*|<tformat|<table|<row|<cell|<frac|\<vartheta\><around*|(|t<rsub|n>|)>|\<pi\>>+S<around*|(|t<rsub|n>|)>-<around*|(|n-<frac|3|2>|)>\<pi\>>|<cell|=0=<frac|\<vartheta\><around*|(|t<rsub|n>|)>|\<pi\>>+<frac|arg<around*|(|\<zeta\><around*|(|<frac|1|2>+it|)>|)>|\<pi\>>-<around*|(|n-<frac|3|2>|)>\<pi\>>>>>><label|ee>
    </equation>

    has a solution for each integer <math|n\<geqslant\>1> where
    <math|t<rsub|n>> enumerate the zeros of <math|Z> on the real line and the
    zeros of <math|\<zeta\>> on the critical line

    <\equation>
      \ \<zeta\><around*|(|<frac|1|2>+i t<rsub|n>|)>=0\<forall\>n\<in\>\<bbb-Z\><rsup|+>
    </equation>

    where <math|><math|\<bbb-Z\><rsup|+>> denotes the positive integers.
    <cite-detail|z0t|Equation 14>
  </conjecture>

  <subsection|The Gram Points>

  The <math|n>-th Gram point is defined to be the solution of the equation

  <\equation>
    \<vartheta\><around*|(|t|)>=<around*|(|n-1|)>\<pi\>
  </equation>

  A very accurate approximation <math|<wide|g|~><around*|(|n|)>> to the Gram
  points is <math|g<around*|(|n|)>> is found by inverting
  <math|<wide|\<vartheta\>|~><around*|(|t|)>> to get the exact solution

  <\equation>
    <tabular*|<tformat|<cwith|1|-1|2|2|cell-halign|l>|<cwith|1|4|2|2|cell-halign|l>|<table|<row|<cell|<wide|g|~><around*|(|n|)>>|<cell|=<around*|{|t:<wide|\<vartheta\>|~><around*|(|t|)>-<around*|(|n-1|)>\<pi\>=0|}>>>|<row|<cell|>|<cell|=<around*|{|t:<around*|(|<frac|t
    ln<around*|(|<frac|t|2 \<pi\>e>|)>|2>-<frac|\<pi\>|8>|)>-<around*|(|n-1|)>\<pi\>=0|}>>>|<row|<cell|>|<cell|=<frac|<around*|(|8n-7|)>\<pi\>|4
    W<around*|(|<frac|8n-7|8 e>|)>>>>|<row|<cell|>|<cell|=g<around*|(|n|)>+O<around*|(|\<delta\><rsub|n>|)>>>>>>
  </equation>

  where <math|W> is the Lambert W function, and the approximation bounds
  <math|\<delta\><rsub|n>> when <math|n=1> is
  <math|\<delta\><rsub|1>=0.00223698\<ldots\>>, followed by
  <math|\<delta\><rsub|2>=0.00137812\<ldots\>> and decreases monotonically
  with increasing <math|n>, that is, <math|\<delta\><rsub|n+1>\<less\>\<delta\><rsub|n>>.
  \ The inverse of <math|><math|<wide|g|~><around*|(|n|)>> is given by\ 

  <\equation>
    <tabular*|<tformat|<cwith|1|-1|1|-1|cell-halign|l>|<table|<row|<cell|<wide|g|~><rsup|-1><around*|(|n|)>>|<cell|=<around*|{|t:<wide|g|~><around*|(|n|)>=0|}>>>|<row|<cell|>|<cell|=<frac|t
    ln<around*|(|<frac|t|2 \<pi\> e>|)>|2
    \<pi\>>+<frac|7|8>>>|<row|<cell|>|<cell|=<wide|N|~><around*|(|t|)>-S<around*|(|t|)>>>|<row|<cell|>|<cell|=<wide|N|~><around*|(|t|)>-S<around*|(|t|)>>>>>>
  </equation>

  where <math|<wide|N|~><around*|(|t|)>>=<math|<wide|N|~><rsub|s><around*|(|t|)>=<wide|N|~><rsub|l><around*|(|t|)>>.

  <subsubsection|An Expression for <math|S<around*|(|t|)>> at its
  Discontinuous Points>

  Define the infinite sequence of functions indexed by
  <math|n\<in\>\<bbb-Z\><rsup|+>>

  <\equation>
    <tabular*|<tformat|<cwith|1|-1|2|2|cell-halign|l>|<table|<row|<cell|T<rsub|n><around*|(|t|)>>|<cell|=1+\<lfloor\><wide|g|~><rsup|-1><around*|(|n|)>\<rfloor\>-n>>|<row|<cell|>|<cell|=1+\<lfloor\><frac|t
    ln<around*|(|<frac|t|2 \<pi\> e>|)>|2 \<pi\>>+<frac|7|8>\<rfloor\>-n>>>>>
  </equation>

  <\proposition>
    For each \Pbad\Q Gram point <math|n> there will be, within
    <math|n\<pm\>2>, a corresponding zero on the critical line which has an
    argument not on the principal branch. That is, if <math|n> is a \Pbad\Q
    Gram point then <math|T<rsub|m><around*|(|t<rsub|m>|)>\<neq\>0> for some
    <math|m> where <math|<around*|\||m-n|\|>\<leqslant\>2>.
  </proposition>

  \ The function <math|T<rsub|n>=T<rsub|n><around*|(|t<rsub|n>|)>> determines
  how many multiples of <math|\<pi\>> to add or subtract to
  <math|-<frac|1|2>-\<lfloor\><frac|\<vartheta\><around*|(|t<rsub|n>|)>|\<pi\>>\<rfloor\>>
  so that it agrees with the argument of <math|\<zeta\>> at a zero on the
  critical line where it is discontinuous, jumping by the multiplicity of the
  corresponding root, and having the value
  <math|<around*|\<nobracket\>|lim<rsub|\<varepsilon\>\<rightarrow\>0>*<frac|1|2><around*|(|<around*|(|S|(>\<rho\>+i
  \<varepsilon\>|)>-S<around*|(|\<rho\>-i \<varepsilon\>|)>|)>> when
  <math|\<zeta\><around*|(|\<rho\>|)>=0>.

  <\definition>
    Let

    <\equation>
      <tabular|<tformat|<cwith|1|10|2|2|cell-halign|l>|<cwith|1|4|2|2|cell-halign|l>|<table|<row|<cell|S<rsub|n><around*|(|t<rsub|>|)>>|<cell|=<frac|1|2>-frac<around*|(|<frac|\<vartheta\><around*|(|t|)>|\<pi\>>|)>-T<rsub|n><around*|(|t|)>>>|<row|<cell|
      >|<cell|=<frac|1|2>-frac<around*|(|<frac|\<vartheta\><around*|(|t|)>|\<pi\>>|)>-<around*|(|1+\<lfloor\><wide|g|~><rsup|-1><around*|(|n|)>\<rfloor\>-n|)>>>|<row|<cell|>|<cell|=<rsub|><frac|1|2>-frac<around*|(|<frac|\<vartheta\><around*|(|t|)>|\<pi\>>|)>-1-\<lfloor\><wide|g|~><rsup|-1><around*|(|n|)>\<rfloor\>+n>>|<row|<cell|>|<cell|=<rsub|>-<frac|1|2>-frac<around*|(|<frac|\<vartheta\><around*|(|t|)>|\<pi\>>|)>-\<lfloor\><wide|g|~><rsup|-1><around*|(|n|)>\<rfloor\>+n>>|<row|<cell|>|<cell|=n<rsub|>-<frac|1|2>-frac<around*|(|<frac|\<vartheta\><around*|(|t|)>|\<pi\>>|)>-\<lfloor\><wide|g|~><rsup|-1><around*|(|n|)>\<rfloor\>>>|<row|<cell|>|<cell|=<rsub|>n-<frac|1|2>-frac<around*|(|<frac|\<vartheta\><around*|(|t|)>|\<pi\>>|)>+\<lfloor\><frac|\<vartheta\><around*|(|t|)>|\<pi\>>+1\<rfloor\>>>|<row|<cell|>|<cell|=n-<frac|1|2>-<around*|(|<frac|\<vartheta\><around*|(|t|)>|\<pi\>>+1|)>>>|<row|<cell|>|<cell|=n-<frac|1|2>-<frac|\<vartheta\><around*|(|t|)>|\<pi\>>-1>>|<row|<cell|>|<cell|=n-<frac|3|2>-<frac|\<vartheta\><around*|(|t|)>|\<pi\>>>>>>>
    </equation>
  </definition>

  Where we see that\ 

  <\equation>
    S<rsub|n><around*|(|t<rsub|n>|)>=n-<frac|3|2>-<frac|\<vartheta\><around*|(|t<rsub|n>|)>|\<pi\>>=S<around*|(|t<rsub|n>|)>
  </equation>

  when <math|t<rsub|n>> is the imaginary part of a zero of <math|\<zeta\>> on
  the critical line.\ 

  <\conjecture>
    <label|c>The normalized argument <math|S<around*|(|t|)>=\<pi\><rsup|-1>arg><math|\<zeta\><around*|(|<frac|1|2>+i
    t|)>> at the n-th non-trivial zero \ <math|\<zeta\><around*|(|<frac|1|2>+i
    t<rsub|n>|)>=0\<forall\>n\<geqslant\>1> on the critical line is equal to
    <math|S<rsub|n><around*|(|t|)>>, that is\ 

    <\equation>
      <tabular*|<tformat|<cwith|1|-1|2|2|cell-halign|l>|<table|<row|<cell|S<around*|(|t<rsub|n>|)>>|<cell|=S<rsub|n><around*|(|t<rsub|n>|)>=<frac|1|2><around*|(|lim<rsub|t\<rightarrow\>t<rsup|-><rsub|n>>S<around*|(|t<rsub|>|)>+lim<rsub|t\<rightarrow\>t<rsup|+><rsub|n>>S<around*|(|t<rsub|>|)>|)>>>|<row|<cell|>|<cell|=n-<frac|3|2>-<frac|\<vartheta\><around*|(|t<rsub|n>|)>|\<pi\>>>>>>><label|een>
    </equation>
  </conjecture>

  <\theorem>
    If Conjecture <reference|c> is true then Conjecture <reference|ec> is
    true and, due to <math|Lemma> <reference|fl>, so is Conjecture
    <reference|RH>, the Riemann hypothesis.
  </theorem>

  <\proof>
    If <math|s<rsub|\<vartheta\>><around*|(|t|)>S<rsub|n><around*|(|t<rsub|n>|)>>=<math|S<around*|(|t<rsub|n>|)>>
    then <math|S<around*|(|t<rsub|n>|)>> is well-defined
    <math|\<forall\>n\<geqslant\>1> since
    <math|s<rsub|\<vartheta\>><around*|(|t|)>S<rsub|n><around*|(|t<rsub|n>|)>>
    is well-defined <math|\<forall\>n\<geqslant\>1>.
  </proof>

  <big-figure|<image|liftedTwisted.eps|5.5in|2.5in||>|<math|S<rsub|n><around*|(|t|)>>
  and the imaginary parts of the roots of
  <math|\<zeta\><around*|(|<frac|1|2>+i t<rsub|n>|)>> marked with verticle
  lines just touching its touching its corresponding pair of neighboring
  curves <math|S<rsub|n><around*|(|t|)>> for <math|n=0\<ldots\>14>>

  <big-figure|<image|liftedTwistedAroundFirstBadGramPoints.eps|5.5in|2.5in||>|<math|S<rsub|n><around*|(|t|)>>
  and the imaginary parts of the roots of
  <math|\<zeta\><around*|(|<frac|1|2>+i t<rsub|n>|)>> marked with verticle
  lines just touching its touching its corresponding pair of neighboring
  curves <math|S<rsub|n><around*|(|t|)>> for <math|n=120\<ldots\>140> which
  includes two \Pbad\Q Gram points at <math|n=126> and <math|n=134>>

  <\theorem>
    <math|arg<around*|(|\<zeta\><around*|(|<frac|1|2>+i
    g<around*|(|n|)>|)>|)>=0\<forall\>n\<in\>\<bbb-Z\><rsup|+>>
  </theorem>

  <\proof>
    The argument of any positive number <math|x> with
    <math|Im<around*|(|x|)>=0> is equal to <math|0> and
    <math|Im<around*|(|\<zeta\><around*|(|<frac|1|2>+i
    g<around*|(|n|)>|)>|)>=0> since by definition the Gram points are the
    points where the imaginary part of <math|\<zeta\>> on the critical line
    vanishes.
  </proof>

  <\conjecture>
    <math|frac<around*|(|<frac|\<vartheta\><around*|(|t|)>|\<pi\>>|)>+S<around*|(|t|)>\<in\><around*|{|-1,0,1|}>\<forall\>0\<less\>t\<in\>\<bbb-R\>>
  </conjecture>

  <big-figure|<image|meet1.eps|5.5in|3.5in||>|Illustration of convergence
  around the first zero>

  <subsection|An Equivalent Expression for <math|S<around*|(|t|)>>>

  Let

  <\equation>
    B<around*|(|t|)>=N<around*|(|t|)>+2=<frac|\<vartheta\><around*|(|t|)>|\<pi\>>+S<around*|(|t|)>+3
  </equation>

  which is Backlund's <math|\<zeta\>> zero counting function on the critical
  strip, plus <math|2>, which is valid without assuming the Riemann
  hypothesis.\ 

  <\theorem>
    An equivalent expression for <math|S<around*|(|t|)>\<forall\>0\<less\>t\<in\>\<bbb-R\>>
    is

    <\equation>
      <tabular*|<tformat|<cwith|1|-1|2|2|cell-halign|l>|<table|<row|<cell|\<cal-S\><around*|(|t|)>>|<cell|=S<rsub|B<around*|(|t|)>><around*|(|t|)>-<frac|3|2>>>|<row|<cell|>|<cell|=B<around*|(|t|)>-<frac|3|2>-<frac|\<vartheta\><around*|(|t|)>|\<pi\>>-<frac|3|2>>>|<row|<cell|>|<cell|=<frac|\<vartheta\><around*|(|t|)>|\<pi\>>+S<around*|(|t|)>+3-<frac|3|2>-<frac|\<vartheta\><around*|(|t|)>|\<pi\>>-<frac|3|2>>>|<row|<cell|>|<cell|=<frac|\<vartheta\><around*|(|t|)>|\<pi\>>+S<around*|(|t|)>+3-3-<frac|\<vartheta\><around*|(|t|)>|\<pi\>>>>|<row|<cell|>|<cell|=<frac|\<vartheta\><around*|(|t|)>|\<pi\>>+S<around*|(|t|)>-<frac|\<vartheta\><around*|(|t|)>|\<pi\>>>>|<row|<cell|>|<cell|=S<around*|(|t|)>>>>>>
    </equation>
  </theorem>

  <\proof>
    Since <math|S<around*|(|t|)>> appears on the left side and right sides of
    the equation, if we subtract <math|S<around*|(|t|)>> from both sides we
    get\ 

    <\equation>
      <tabular|<tformat|<cwith|1|1|2|2|cell-halign|l>|<table|<row|<cell|0>|<cell|=<frac|\<vartheta\><around*|(|t|)>|\<pi\>>+3-<frac|3|2>-<frac|\<vartheta\><around*|(|t|)>|\<pi\>>-<frac|3|2>>>|<row|<cell|>|<cell|=3-<frac|3|2>-<frac|3|2>>>|<row|<cell|>|<cell|=3-3>>|<row|<cell|>|<cell|=0>>>>>
    </equation>
  </proof>

  <section|Appendix>

  <subsection|Transcendental Equations Satisifed By The Nontrivial Riemann
  Zeros>

  <\definition>
    The <verbatim|critical line> is the line in the complex plane defined by
    <math|Re<around*|(|t|)>=<frac|1|2>>.
  </definition>

  <\definition>
    The <verbatim|critical strip> is the strip in the complex plane defined
    by <math|0\<less\>Re<around*|(|t|)>\<less\>1>.
  </definition>

  <\theorem>
    <label|le>If the limit

    <\equation>
      lim<rsub|\<delta\>\<rightarrow\>0<rsup|+>>arg<around*|(|\<zeta\><around*|(|<frac|1|2>+\<delta\>+i
      t|)>|)>
    </equation>

    is exists and is well-defined <math|\<forall\>t> then the left-hand side
    of Equation (<reference|ae>) is well-defined <math|\<forall\>t>, and due
    to monotonicity, there must be a unique solution for every
    <math|n\<in\>\<bbb-Z\><rsup|+>>. <cite-detail|z0t|II.A>\ 
  </theorem>

  <\conjecture>
    <label|RH>(The Riemann hypothesis) All solutions <math|t> of the equation

    <\equation>
      \<zeta\><around*|(|t|)>=0
    </equation>

    besides the trivial solutions <math|t=-2n> with
    <math|n\<in\>\<bbb-Z\><rsup|+>> have real-part <math|<frac|1|2>>, that
    is, <math|Re<around*|(|t|)>=<frac|1|2>> when
    <math|\<zeta\><around*|(|t|)>=0> and <math|t\<neq\>-2n>.
  </conjecture>

  <\lemma>
    <label|fl>If the exact Equation (<reference|ee>) has a unique solution
    for each <math|n\<in\>\<bbb-Z\><rsup|+>> then Conjecture <reference|RH>,
    the Riemann hypothesis, follows.
  </lemma>

  <\proof>
    If the exact equation has a unique solution for each <math|n>, then the
    zeros obtained from its solutions on the <verbatim|critical line> can be
    counted since they are enumerated by the integer <math|n>, leading to the
    counting function <math|N<rsub|0><around*|(|t|)>> in Equation
    (<reference|N0>). The number of solutions obtained on the
    <verbatim|critical line> would saturate the counting function of the
    number of solutions on the <verbatim|critical strip> so that
    <math|N<around*|(|t|)>=N<rsub|0><around*|(|t|)>> and thus all of the
    non-trivial zeros of <math|\<zeta\>> would be enumerated in this manner.
    If there are zeros off of the critical line, or zeros with multiplicity
    <math|m\<geqslant\>2>, then the exact Equation (<reference|ee>) would
    fail to capture all the zeros on the critical strip which would mean
    <math|N<rsub|0><around*|(|t|)>\<less\>N<around*|(|t|)>>.
    \ <cite-detail|z0t|IX>
  </proof>

  \;

  <\bibliography|bib|tm-plain|references.bib>
    <\bib-list|1>
      <bibitem*|1><label|bib-z0t>Guilherme França<localize| and >André
      LeClair.<newblock> Transcendental equations satisfied by the individual
      zeros of riemann zeta, dirichlet and modular l-functions.<newblock>
      <with|font-shape|italic|Communications in Number Theory and Physics>,
      2015.<newblock>
    </bib-list>
  </bibliography>
</body>

<\initial>
  <\collection>
    <associate|page-medium|paper>
  </collection>
</initial>

<\references>
  <\collection>
    <associate|N0|<tuple|8|2>>
    <associate|RH|<tuple|16|6>>
    <associate|ae|<tuple|7|2>>
    <associate|auto-1|<tuple|1|1>>
    <associate|auto-10|<tuple|2|6>>
    <associate|auto-11|<tuple|2.1|6>>
    <associate|auto-12|<tuple|17|6>>
    <associate|auto-2|<tuple|1.1|1>>
    <associate|auto-3|<tuple|1.1.1|1>>
    <associate|auto-4|<tuple|1.2|2>>
    <associate|auto-5|<tuple|1.2.1|3>>
    <associate|auto-6|<tuple|1|4>>
    <associate|auto-7|<tuple|2|4>>
    <associate|auto-8|<tuple|3|5>>
    <associate|auto-9|<tuple|1.3|5>>
    <associate|bib-z0t|<tuple|1|6>>
    <associate|c|<tuple|8|4>>
    <associate|ec|<tuple|5|2>>
    <associate|ee|<tuple|9|2>>
    <associate|een|<tuple|17|4>>
    <associate|fl|<tuple|17|6>>
    <associate|le|<tuple|15|6>>
  </collection>
</references>

<\auxiliary>
  <\collection>
    <\associate|bib>
      z0t

      z0t

      z0t

      z0t

      z0t

      z0t

      z0t
    </associate>
    <\associate|figure>
      <tuple|normal|<with|mode|<quote|math>|S<rsub|n><around*|(|t|)>> and the
      imaginary parts of the roots of <with|mode|<quote|math>|\<zeta\><around*|(|<frac|1|2>+i
      t<rsub|n>|)>> marked with verticle lines just touching its touching its
      corresponding pair of neighboring curves
      <with|mode|<quote|math>|S<rsub|n><around*|(|t|)>> for
      <with|mode|<quote|math>|n=0\<ldots\>14>|<pageref|auto-6>>

      <tuple|normal|<with|mode|<quote|math>|S<rsub|n><around*|(|t|)>> and the
      imaginary parts of the roots of <with|mode|<quote|math>|\<zeta\><around*|(|<frac|1|2>+i
      t<rsub|n>|)>> marked with verticle lines just touching its touching its
      corresponding pair of neighboring curves
      <with|mode|<quote|math>|S<rsub|n><around*|(|t|)>> for
      <with|mode|<quote|math>|n=120\<ldots\>140> which includes two \Pbad\Q
      Gram points at <with|mode|<quote|math>|n=126> and
      <with|mode|<quote|math>|n=134>|<pageref|auto-7>>

      <tuple|normal|Illustration of convergence around the first
      zero|<pageref|auto-8>>
    </associate>
    <\associate|toc>
      <vspace*|1fn><with|font-series|<quote|bold>|math-font-series|<quote|bold>|1<space|2spc>Introduction>
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-1><vspace|0.5fn>

      <with|par-left|<quote|1tab>|1.1<space|2spc>Motivation
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-2>>

      <with|par-left|<quote|2tab>|1.1.1<space|2spc>The Riemann-Siegel
      <with|mode|<quote|math>|\<vartheta\><around*|(|t|)>> Function
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-3>>

      <with|par-left|<quote|1tab>|1.2<space|2spc>The Gram Points
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-4>>

      <with|par-left|<quote|2tab>|1.2.1<space|2spc>An Expression for
      <with|mode|<quote|math>|S<around*|(|t|)>> at its Discontinuous Points
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-5>>

      <with|par-left|<quote|1tab>|1.3<space|2spc>An Equivalent Expression for
      <with|mode|<quote|math>|S<around*|(|t|)>>
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-9>>

      <vspace*|1fn><with|font-series|<quote|bold>|math-font-series|<quote|bold>|2<space|2spc>Appendix>
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-10><vspace|0.5fn>

      <with|par-left|<quote|1tab>|2.1<space|2spc>Transcendental Equations
      Satisifed By The Nontrivial Riemann Zeros
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-11>>

      <vspace*|1fn><with|font-series|<quote|bold>|math-font-series|<quote|bold>|Bibliography>
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-12><vspace|0.5fn>
    </associate>
  </collection>
</auxiliary>