<TeXmacs|1.99.5>

<style|article>

<\body>
  <doc-data|<doc-title|A Sequence of Cauchy Sequences Which Is Conjectured to
  Converge to the Imaginary Parts of the Zeros of the Riemann Zeta
  Function>|<doc-author|<author-data|<author-name|Stephen Crowley
  \<less\>stephencrowley214@gmail.com\<gtr\>>|<\author-affiliation>
    <date|>
  </author-affiliation>>>>

  <abstract-data|<\abstract>
    A sequence of Cauchy sequences which conjecturally converge to the
    Riemann zeros is constructed and related to the LeClair-França criteria
    for the Riemann hypothesis.\ 
  </abstract>>

  \;

  <\table-of-contents|toc>
    <with|par-left|1tab|1<space|2spc>Transcendental Equations Satisifed By
    The Nontrivial Riemann Zeros <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
    <no-break><pageref|auto-1>>

    <with|par-left|2tab|1.1<space|2spc>An Iteration Function Which
    Successively Removes Roots <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
    <no-break><pageref|auto-3>>

    <with|par-left|2tab|1.2<space|2spc>Indifferent Fixed-Points
    <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
    <no-break><pageref|auto-4>>

    <with|par-left|2tab|1.3<space|2spc>Alternating Attractive and Repulsive
    Fixed-Points <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
    <no-break><pageref|auto-5>>

    <with|par-left|2tab|1.4<space|2spc>Sequential Convergence to the Nearest
    Fixed-Points <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
    <no-break><pageref|auto-8>>

    <vspace*|1fn><with|font-series|bold|math-font-series|bold|1<space|2spc>Appendix>
    <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
    <no-break><pageref|auto-9><vspace|0.5fn>

    <vspace*|1fn><with|font-series|bold|math-font-series|bold|Bibliography>
    <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
    <no-break><pageref|auto-10><vspace|0.5fn>
  </table-of-contents>

  \;

  <section|Introduction>

  The aim of this paper is to introduce a complex dynamical system which
  converges to the zeros of the Riemann zeta function.

  <subsection|Transcendental Equations Satisifed By The Nontrivial Riemann
  Zeros>

  <\definition>
    The <verbatim|exact equation> for the <math|n>-th zero of the Hardy
    <math|Z> function <math|y<rsub|n>> is given by <cite-detail|z0t|Equation
    20>

    <\equation>
      \<vartheta\><around*|(|y<rsub|n>|)>+S<around*|(|y<rsub|n>|)>=<around*|(|n-<frac|3|2>|)>\<pi\><label|ee>
    </equation>

    where <math|y<rsub|n>> enumerate the zeros of <math|Z> on the real line
    and the zeros of <math|\<zeta\>> on the critical line

    <\equation>
      \<Zeta\><around*|(|y<rsub|n>|)>=0 and \<zeta\><around*|(|<frac|1|2>+i
      y<rsub|n>|)>=0\<forall\>n\<in\>\<bbb-Z\><rsup|+>
    </equation>

    where <math|><math|\<bbb-Z\><rsup|+>> denotes the positive integers.
    <cite-detail|z0t|Equation 14>\ 
  </definition>

  \ By replacing the <math|ln\<Gamma\>> function in (<reference|vartheta>)
  with Stirling's asymptotic expansion as in <cite-detail|z0t|Equation 13> we
  get

  <\equation>
    <wide|\<vartheta\>|~><around*|(|t|)>=<frac|t |2>ln<around*|(|<frac|t|2
    \<pi\> e>|)>-<frac|\<pi\>|8>+O<around*|(|t<rsup|-1>|)>
  </equation>

  and substitute <math|\<vartheta\><around*|(|t|)>> with
  <math|<wide|\<vartheta\>|~><around*|(|t|)>> in Equation <reference|ee>
  which leads to

  <\definition>
    The <verbatim|asymptotic equation> for the <math|n>-th zero of the Hardy
    <math|Z> function

    <\equation>
      <frac|t<rsub|n>|2 \<pi\>>ln<around*|(|<frac|t<rsub|n>|2 \<pi\>
      t>|)>+S<around*|(|t<rsub|n>|)>=n-<frac|11|8><label|ae>
    </equation>

    <cite-detail|z0t|Equation 20>
  </definition>

  <big-figure|<image|approxVsExact.eps|6in|3in||>|The functions
  <math|\<vartheta\><around*|(|y<rsub|n>|)>+S<around*|(|y<rsub|n>|)>-<around*|(|n-<frac|3|2>|)>\<pi\>>
  and <math|<wide|\<vartheta\>|~><around*|(|y<rsub|n>|)>+S<around*|(|y<rsub|n>|)>-<around*|(|n-<frac|3|2>|)>\<pi\>>
  for <math|n=1,2> with the zeros at <math|y<rsub|1>> and <math|y<rsub|2>>
  marked with vertical lines. >

  <\remark>
    The fact that the exact and asymptotic equations have two solutions when
    <math|n=1> can be understood by noting that Equations (<reference|ee>)
    and (<reference|ae>) are derived from the equation

    <\equation>
      n=<wide|\<vartheta\>|~><around*|(|t|)>+<frac|\<pi\>|8>-<frac|5|8>+S<around*|(|t<rsub|>|)><label|ord>
    </equation>

    which has a minimum in the interval <math|<around*|(|-2,-1|)>> and thus
    <math|n\<geqslant\>-1> so that, in order to follow the convention that
    the zeros are enumerated by the positive integers, the substituion
    <math|n\<rightarrow\>n-2> is made in Equation (<reference|ord>) so that

    <\equation>
      n-2=<wide|\<vartheta\>|~><around*|(|t|)>+<frac|\<pi\>|8>-<frac|5|8>+S<around*|(|t<rsub|>|)>
    </equation>

    <cite-detail|z0t|Equation 12>
  </remark>

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

  <\corollary>
    The number of solutions of Equation (<reference|ae>) over the interval
    <math|<around*|[|0,t|]>> is given by

    <\equation>
      N<rsub|0><around*|(|t|)>=<frac|t|2 \<pi\>>ln<around*|(|<frac|t|2
      \<pi\>e>|)>+<frac|7|8>+S<around*|(|t|)>+O<around*|(|t<rsup|-1>|)><label|N0>
    </equation>
  </corollary>

  which counts the number of zeros <verbatim|on the critical line>.

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
    <verbatim|critical line> would saturate counting function of the number
    of solutions on the <verbatim|critical strip> so that
    <math|N<around*|(|t|)>=N<rsub|0><around*|(|t|)>> and thus all of the
    non-trivial zeros of <math|\<zeta\>> would be enumerated in this manner.
    If there are zeros off of the critical line, or zeros with multiplicity
    <math|m\<geqslant\>2>, then the exact Equation (<reference|ee>) would
    fail to capture all the zeros on the critical strip which would mean
    <math|N<rsub|0><around*|(|t|)>\<less\>N<around*|(|t|)>>.
    \ <cite-detail|z0t|IX>
  </proof>

  <\corollary>
    The Riemann hypothesis(RH) is not necesarily false if the exact Equation
    (<reference|ee>) does not have a unique solution for every <math|n>,
    since the solutions could still be on the critical line but not
    necessarily simple, that is, a root on the critical line could have
    multiplicity <math|m\<geqslant\>2> and the RH would still be true.
  </corollary>

  <\corollary>
    The Riemann hypothesis is true and all of the zeros on the critical line
    are simple if the exact Equation (<reference|ee>) has a unique solution
    for each <math|n\<in\>\<bbb-Z\><rsup|+>>. <cite-detail|z0t|IX>
  </corollary>

  <subsubsection|An Iteration Function Which Successively Removes Roots>

  <\definition>
    Let

    <\equation*>
      Y<rsub|n,m><around*|(|t|)>=<choice|<tformat|<table|<row|<cell|t>|<cell|m=0>>|<row|<cell|t+h<rsub|n,m>cos<around*|(|*\<pi\>
      n|)>tanh<around*|(|<frac|Z<around*|(|Y<rsub|n,m-1><around*|(|t|)>|)>|<around*|\||\<Omega\><around*|(|t|)>|\|><big|prod><rsub|k=1><rsup|n-1>tanh<around*|(|Y<rsub|n,m-1><around*|(|t|)>-y<rsub|k>|)>>|)>>|<cell|m\<geqslant\>1>>>>>
    </equation*>

    denote the <math|m>-th iterate of the <math|n>-th iteration function
    corresponding to the <math|n>-th zero of the Hardy <math|Z> function
    where

    <\equation>
      \<Omega\><around*|(|t|)>=<choice|<tformat|<table|<row|<cell|1>|<cell|t=e>>|<row|<cell|e<rsup|<frac|3|4><sqrt|<frac|log<around*|(|t|)>|log<around*|(|log<around*|(|t|)>|)>>>>>|<cell|t\<neq\>e>>>>>
    </equation>

    is a lower bound for the running maximum of
    <math|<around*|\||Z<around*|(|s|)>|\|>>

    <\equation>
      max<rsub|0\<leqslant\>s\<leqslant\>t><around*|\||Z<around*|(|s|)>|\|>\<gtr\>\<Omega\><around*|(|t|)>\<forall\>t\<geqslant\>45.590\<ldots\>
    </equation>

    ensuring that\ 

    <\equation>
      <frac|<around*|\||Z<around*|(|t|)>|\|>|\<Omega\><around*|(|t|)>>\<gtr\>0\<forall\>t\<geqslant\>45.590\<ldots\>
    </equation>

    which normalizes the range of <math|Z<around*|(|t|)>> which is known to
    grow in both maximum and average value as <math|t\<rightarrow\>\<infty\>>
    and <math|h<rsub|n,m>> is factor which influences the rate of convergence\ 

    <\equation>
      h<rsub|n,m>=<choice|<tformat|<table|<row|<cell|1>|<cell|m\<leqslant\>2>>|<row|<cell|h<rsub|n,m-1>>|<cell|sign<around*|(|\<Delta\>Y<rsup|><rsub|n,m-2><around*|(|t|)>|)>=sign<around*|(|\<Delta\>Y<rsup|><rsub|n,m-1><around*|(|t|)>|)>>>|<row|<cell|<frac|h<rsub|n,m-1>|2>>|<cell|sign<around*|(|\<Delta\>Y<rsup|><rsub|n,m-2><around*|(|t|)>|)>\<neq\>sign<around*|(|\<Delta\>Y<rsup|><rsub|n,m-1><around*|(|t|)>|)>>>>>>
    </equation>

    where

    <\equation>
      \<Delta\>Y<rsub|n,m><around*|(|t|)>=Y<rsub|n,m><around*|(|t|)>-Y<rsub|n,m-1><around*|(|t|)>
    </equation>

    is the <math|1>-st difference of the <math|m>-th iterate for the
    <math|n>-th zero. <cite-detail|ramachandra1995lectures|Theorem 3.2.3>\ 
  </definition>

  <\lemma>
    The roots of <math|Z<around*|(|t|)>> are fixed-points of
    <math|Y<rsub|n,m><around*|(|t|)>\<forall\>n,m\<in\>\<bbb-Z\><rsup|+>>.
  </lemma>

  <\proof>
    If <math|Z<around*|(|t|)>=0> then <math|tanh<around*|(|<frac|Z<around*|(|t|)>|<around*|\||\<Omega\><around*|(|t|)>|\|><big|prod><rsub|k=1><rsup|n-1>tanh<around*|(|t-y<rsub|k>|)>>|)>=tanh<around*|(|<frac|0|<around*|\||\<Omega\><around*|(|t|)>|\|><big|prod><rsub|k=1><rsup|n-1>tanh<around*|(|t-y<rsub|k>|)>>|)>=tanh<around*|(|0|)>=0>
    so that <math|Y<rsub|n><around*|(|t|)>=t+cos<around*|(|\<pi\>n|)>0=t+0=t>
    when <math|Z<around*|(|t|)>=0>.
  </proof>

  <subsubsection|Indifferent Fixed-Points of
  <math|Y<rsub|n,m><around*|(|t|)>>>

  <\theorem>
    <math|Y<rsub|n,m><around*|(|t|)>> has indifferent fixed-points at each
    point <math|y<rsub|k>> where <math|k=1\<ldots\>n-1>
  </theorem>

  <\proof>
    The product in the denominator <math|<big|prod><rsub|k=1><rsup|n-1>tanh<around*|(|t-y<rsub|k>|)>\<rightarrow\>0>
    smoothly as <math|t> approaches any <math|y<rsub|k>\<in\><big|cup><rsub|k=1><rsup|n-1>y<rsub|k>>
    since <math|tanh<around*|(|0|)>=0> and <math|tanh> is a smooth function.
    When any element of the product is zero the value of the product is zero
    regardless of the values of any other elements of the product. Since
    <math|<frac|1|s>\<rightarrow\>\<infty\>> as <math|s\<rightarrow\>0> and
    <math|tanh<around*|(|<around*|\||x|\|>|)>\<rightarrow\>1> as
    <math|<around*|\||x|\|>\<rightarrow\>\<infty\>> we have
    <math|tanh<around*|(|\<infty\>|)>=1> and
    <math|tanh<around*|(|-\<infty\>|)>=-1> so that
    <math|Y<rsub|n><around*|(|t|)>=t+cos<around*|(|\<pi\>n|)>\<forall\>t\<in\><big|cup><rsub|k=1><rsup|n-1>y<rsub|k>>.
    Since <math|Y<rsub|n><around*|(|t|)>=t\<pm\>1\<forall\>t\<in\><big|cup><rsub|k=1><rsup|n-1>y<rsub|k>>
    when <math|n> is an integer, we see that
    <math|<frac|\<mathd\>|\<mathd\>t>Y<rsub|n><around*|(|t|)>=<frac|\<mathd\>|\<mathd\>t><around*|(|t\<pm\>1|)>=1>
    so that the multiplier <math|\<lambda\><rsub|Y<rsub|n><around*|(|t|)>>=<around*|\||<frac|\<mathd\>|\<mathd\>t>Y<rsub|n><around*|(|t|)>|\|>=1\<forall\>><math|t\<in\><big|cup><rsub|k=1><rsup|n-1>y<rsub|k>>.
  </proof>

  <\theorem>
    <math|Y<rsub|n,m><around*|(|t|)>> has indifferent fixed points at each
    trivial zero <math|-<frac|i|2><around*|(|-4n-1|)>> where
    <math|Z<around*|(|-<frac|i|2><around*|(|-4n-1|)>|)>=0\<forall\>n\<in\>\<bbb-Z\><rsup|+<rsub|>>>.
  </theorem>

  <\proof>
    Since <math|<frac|\<mathd\>|\<mathd\>t><around*|(|f<around*|(|t|)>+g<around*|(|t|)>|)>=<wide|f|\<dot\>><around*|(|t|)>*g<around*|(|t|)>+f<around*|(|t|)><wide|g|\<dot\>><around*|(|t|)>>
    and <math|Z<around*|(|t|)>=e<rsup|i\<vartheta\><around*|(|t|)>>\<zeta\><around*|(|<frac|1|2>+i
    t|)>> it suffices to show that <math|lim<rsub|t\<rightarrow\>-<frac|i|2><around*|(|-4n-1|)>><around*|\||<frac|\<mathd\>|\<mathd\>t>e<rsup|i\<vartheta\><around*|(|t|)>>|\|>=\<infty\>>.
    Since <math|<frac|\<mathd\>|\<mathd\>t>e<rsup|i\<vartheta\><around*|(|t|)>>=i<around*|(|<frac|<frac|i\<Psi\><around*|(|<frac|1|4>-<frac|i
    t|2>|)>|2>-<frac|i\<Psi\><around*|(|<frac|1|4>+<frac|i
    t|2>|)>|2>|2>-<frac|ln<around*|(|\<pi\>|)>|2>|)>e<rsup|i\<vartheta\><around*|(|t|)>>>
    we only have to check that <math|lim<rsub|t\<rightarrow\><frac|i|2><around*|(|-4n-1|)>><around*|\||\<Psi\><around*|(|<frac|1|4>+<frac|i
    t|2>|)>|\|>=lim<rsub|t\<rightarrow\>-<frac|i|2><around*|(|-4n-1|)>><around*|\||\<Psi\><around*|(|<frac|1|4>+<frac|i
    t|2>|)>|\|>=\<infty\>> which is true since <math|\<Psi\><around*|(|t|)>>
    has poles at <math|t=1-n\<forall\>n\<in\>\<bbb-Z\><rsup|+>> where
    <math|\<Psi\><around*|(|t|)>=<frac|<wide|\<Gamma\>|\<dot\>><around*|(|t|)>|\<Gamma\><around*|(|t|)>>>
    <math|>and <math|\<Gamma\><around*|(|t|)>> has poles at
    <math|t=1-n><math|\<forall\>n\<in\>\<bbb-Z\><rsup|+>>. Since
    <math|<around*|\||tanh<around*|(|\<infty\>|)>|\|>=1> the multiplier is
    equal to 1 at each <math|-<frac|i|2><around*|(|-4n-1|)>>.
  </proof>

  <subsubsection|Alternating Attractive and Repulsive Fixed-Points>

  <\proposition>
    <label|podd>When <math|n> is an odd number,
    <math|Y<rsub|n><around*|(|t|)>> has attractive fixed-points at the
    odd-numbered roots <math|y<rsub|2k-1>\<forall\>2k-1\<geqslant\>n> and
    repulsive fixed-points at the even-numbered roots
    <math|y<rsub|2k>\<forall\>2k\<geqslant\>n>.
  </proposition>

  <\proposition>
    <label|peven>When <math|n> is an even number,
    <math|Y<rsub|n><around*|(|t|)>> has attractive fixed-points at the
    even-numbered roots <math|y<rsub|2k>\<forall\>2k\<geqslant\>n> and
    repulsive fixed-points at the odd-numbered roots
    <math|y<rsub|2k-1>\<forall\>2k-1\<geqslant\>n>.
  </proposition>

  <big-figure|<image|tanhZOmega.eps|6.5in|2.5in||>|<math|The> functions which
  are subtracted or added to <math|t> to get
  <math|Y<rsub|1><around*|(|t|)>,Y<rsub|2><around*|(|t|)>,Y<rsub|3><around*|(|t|)>,Y<rsub|4><around*|(|t|)>>.
  When <math|n> is odd <math|cos<around*|(|\<pi\>n|)>=-1> so that the the
  value is subtracted from <math|t>, when <math|n> is even
  <math|cos<around*|(|2\<pi\>|)>=1> so it is added. It is plain to see that
  the curves <math|><math|tanh<around*|(|<frac|Z<around*|(|t|)>|\<Omega\><around*|(|t|)><big|prod><rsub|k=1><rsup|n-1>tanh<around*|(|t-y<rsub|k>|)>>|)>>
  do not cross the zero axis for any <math|t\<less\>y<rsub|n>> >

  \;

  <big-figure|<image|mult.eps|6.5in|2in||>|Multipler<math|> of the maps
  <math|Y<rsub|1><around*|(|t|)>> and <math|Y<rsub|3><around*|(|t|)>>>

  <\remark>
    The function <math|h<rsub|n,m>> is defined to be <math|1> when
    <math|1\<leqslant\>m\<leqslant\>2>. If
    <math|sign<around*|(|\<Delta\>Y<rsup|><rsub|n,m-1><around*|(|t|)>|)>\<neq\>sign<around*|(|\<Delta\>Y<rsup|><rsub|n,m><around*|(|t|)>|)>>
    then <math|h<rsub|n,m+1>=<frac|h<rsub|n,m>|2>> so that the convergence
    rate is \ halved when the sign of the difference between successive
    iterates changes, indicating that it jumped across the root. This
    prevents the sequence generated by the iteration from getting stuck in an
    artifical <math|2>-cycle and jumping back and forth across the root with
    equal magnitude indefinately when implementing this method with
    finite-precision arithmetic on a digital computer. Without this
    successive relaxation, the iterates still converge in theory however the
    number of iterations required could be several million or higher, while
    still having the difficulty of possibly getting stuck in a <math|2>-cycle
    in computer implementations.
  </remark>

  <\theorem>
    <label|cm>The Lipschitz constant <math|M> of the map
    <math|Y<rsub|n,m><around*|(|t|)>\<less\>1\<forall\>t\<gtr\>e> therefore
    <math|Y<rsub|n,m><around*|(|t|)>> is a contraction mapping\ 

    <\equation>
      <around*|\||Y<rsub|n,m><around*|(|t|)>-Y<rsub|n,m><around*|(|s|)>|\|>\<leqslant\>M<around*|\||t-s|\|>
    </equation>
  </theorem>

  <\proof>
    The Lipschitz constant of a continuous differentiable function
    <math|f<around*|(|x|)>> is equal to the maximum absolute value of its
    derivative\ 

    <\equation>
      M=sup<rsub|x><around*|\||<frac|\<mathd\>|\<mathd\>x>f<around*|(|x|)>|\|>
    </equation>

    The derivative of <math|t-tanh<around*|(|t|)>> is
    <math|tanh<around*|(|t|)><rsup|2>>. Since the maximum absolute value of
    <math|tanh<around*|(|t|)>> is 1 then the maximum value of its square is
    also <math|1>. Since <math|\<Omega\><around*|(|t|)>\<gtr\>1> and
    <math|h<rsub|n,m>\<leqslant\>1> the derivative
    <math|<frac|\<mathd\>|\<mathd\>t>Y<rsub|n,m><around*|(|t|)>> can never
    have an absolute value <math|\<geqslant\>1> since that would require
    <math|<around*|\||tanh<around*|(|<frac|Z<around*|(|t|)>|<around*|\||\<Omega\><around*|(|t|)>|\|><big|prod><rsub|k=1><rsup|n-1>tanh<around*|(|t-y<rsub|k>|)>>|)>|\|>=1>
    which is only possible if <math|Z<around*|(|t|)>=\<pm\>\<infty\>> which
    is only the case when <math|t=\<pm\><frac|i|2>> which corresponds to the
    pole at <math|\<zeta\><around*|(|1|)>>. Since
    <math|Z<around*|(|t|)>\<in\>\<bbb-R\>> when <math|t\<in\>\<bbb-R\>> it
    can never be the case that <math|Z<around*|(|t|)>=\<infty\>> so that
    <math|<around*|\||<frac|\<mathd\>|\<mathd\>t>Y<rsub|n,m><around*|(|t|)>|\|>\<neq\>1\<forall\>t\<in\>\<bbb-R\>>
    and the Lipschitz constant <math|M> is strictly less than 1.
  </proof>

  <subsubsection|Sequential Convergence to the Nearest Fixed-Points>

  <\proposition>
    <label|p1>The limit\ 

    <\equation>
      y<rsub|n>=lim<rsub|m\<rightarrow\>\<infty\>>Y<rsub|n,m><around*|(|s<rsub|n>|)>
    </equation>

    where

    <\equation>
      s<rsub|n>=<choice|<tformat|<table|<row|<cell|14>|<cell|n=1>>|<row|<cell|21>|<cell|n=2>>|<row|<cell|<frac|y<rsub|n-1>+y<rsub|n-2>|2>>|<cell|n\<geqslant\>3>>>>>
    </equation>

    exists and is equal to the <math|n>-th zero of the Hardy Z function for
    all integer <math|n\<subset\>\<bbb-Z\><rsup|+>>. That is,
    <math|Y<rsub|n,m><around*|(|z<rsub|n>|)>> forms a Cauchy sequence, due to
    the contraction mapping property proved in Theorem <reference|cm> whose
    elements are indexed by <math|m> converging to the <math|n>-th root
    <math|y<rsub|n>> where the <math|n>-th starting point is defined to be
    half-way between the <math|<around*|(|n-2|)>>-th and the
    <math|<around*|(|n-1|)>>-th root <math|y<rsub|n>> when <math|n\<gtr\>2>
    and equal to a point close to the first known zero at
    <math|14.134\<ldots\>.> when <math|n=1> and a point close to the
    <math|2nd> zero at <math|21.022\<ldots\>> when <math|n=2>
  </proposition>

  <\remark>
    The mid-way point between the nearest neighbors to the left of
    <math|y<rsub|n>> is used as the starting point for the iteration since
    any point less than <math|y<rsub|n>> and greater than <math|e> is within
    the immediate basin of attraction of <math|y<rsub|n>>. The precise
    location of any roots <math|y<rsub|p>> where <math|p\<less\>n> cannot be
    used as a starting point since the map <math|Y<rsub|p,m><around*|(|t|)>>
    is a non-expansive mapping with Lipschitz constant precisely equal to 1
    when <math|t\<in\><big|cup><rsup|p-1><rsub|k=1>y<rsub|k>> so that the
    hyperbolic tangent has an argument of infinity resulting in a value of 1.
    Trajectories are neither attracted or repelled to any point
    <math|<big|cup><rsup|n-1><rsub|k=1>y<rsub|k>> under the action of the map
    <math|Y<rsub|n,m><around*|(|t|)>> however, trajectories started precisely
    on any point <math|t\<in\><big|cup><rsup|n-1><rsub|k=1>y<rsub|k>> will
    never attain a value other than <math|t> since any <math|y<rsub|k>> is a
    fixed-point of <math|Y<rsub|n,m><around*|(|t|)>>.\ 
  </remark>

  <\note>
    The truth of Propositon <reference|p1> has been verified computationally
    up to <math|n=800,000> with a computer program which implements the
    methods described here using the arbitrary precision complex ball
    arithmiticlibrary arblib<cite|Johansson2017arb> and compares the results
    against the tables published by Andrew Odlyzko<cite|zt>. Also, the fact
    that for <math|n=1> and <math|n=2> the values of the initial conditions
    are arbitarily selected in the vicinity of the known roots
    <math|s<rsub|1>=>14 and <math|s<rsub|2>=>21 and this does not detract
    from the validity of the statements are those derived from them.
  </note>

  <\theorem>
    The Cauchy sequence <math|lim<rsub|m\<rightarrow\>\<infty\>>Y<rsub|n,m><around*|(|s<rsub|n>|)>>
    will never converge to any <math|y<rsub|k>> where <math|k\<less\>n>.\ 
  </theorem>

  <\proof>
    All <math|y<rsub|k>> are indifferent fixed-points of
    <math|Y<rsub|n,m><around*|(|t|)>> and the trajectories generated by
    <math|Y<rsub|n,m><around*|(|s<rsub|n>|)>> are never started from a point
    <math|y<rsub|k>> since <math|s<rsub|n>\<nin\><big|cup><rsub|k=1><rsup|n-1>y<rsub|k>>
    and the only way <math|Y<rsub|n,m><around*|(|t|)>> would
    <math|<rprime|''>convege<rprime|''>> to an indifferent fixed-point is if
    it was started precisely on one, and <math|s<rsub|n>> is by definition
    equal to the mid-point between successive <math|y<rsub|n>>.
  </proof>

  <\theorem>
    <label|pc>The Cauchy sequence <math|Y<rsub|n,m><around*|(|s<rsub|n>|)>>
    will never converge to any <math|y<rsub|n+2k-1>\<forall\>k\<in\>\<bbb-Z\><rsup|+><rsup|><rsup|>>
    if Proposition <reference|podd> is true.
  </theorem>

  <\proof>
    If Propositions <reference|podd> is true then <math|y<rsub|n+2k-1>> are
    repelling fixed-points for <math|Y<rsub|n,m><around*|(|t|)>>.
  </proof>

  <\note>
    If Propositions <reference|podd> and <reference|peven> are true then
    <math|Y<rsub|n,m><around*|(|s<rsub|n>|)>> will never converge to
    <math|y<rsub|q>> with <math|q> odd and <math|n> even nor to
    <math|y<rsub|r>> with <math|r> even and <math|n> odd. It suffices to
    prove that <math|Y<rsub|n,m><around*|(|s<rsub|n>|)>\<less\>y<rsub|n+1>\<forall\>n,m\<in\>\<bbb-Z\><rsup|+>>
    which would mean that <math|Y<rsub|n,m><around*|(|s<rsub|n>|)>> can never
    jump across the repelling fixed-point at <math|y<rsub|n+1>> to land on
    any of the attractive fixed-points in
    <math|<big|cup><rsub|k=1><rsup|\<infty\>>y<rsub|n+2k>>
  </note>

  <\lemma>
    Let

    <\equation>
      Y<rsup|+><rsub|n,m><around*|(|t|)>=<choice|<tformat|<table|<row|<cell|t>|<cell|m=0>>|<row|<cell|t+h<rsub|n,m>cos<around*|(|*\<pi\>
      n|)>tanh<around*|(|<frac|Z<around*|(|Y<rsub|n,m-1><around*|(|t|)>|)>|<around*|\||\<Omega\><around*|(|t|)>|\|><big|prod><rsub|k=1><rsup|n-1>tanh<around*|(|Y<rsub|n,m-1><around*|(|t|)>-y<rsub|k>|)>>|)>>|<cell|m\<geqslant\>1>>>>>
    </equation>

    <\equation>
      Y<rsup|-><rsub|n,m><around*|(|t|)>=<choice|<tformat|<table|<row|<cell|t>|<cell|m=0>>|<row|<cell|t-h<rsub|n,m>cos<around*|(|*\<pi\>
      n|)>tanh<around*|(|<frac|Z<around*|(|Y<rsub|n,m-1><around*|(|t|)>|)>|<around*|\||\<Omega\><around*|(|t|)>|\|><big|prod><rsub|k=1><rsup|n-1>tanh<around*|(|Y<rsub|n,m-1><around*|(|t|)>-y<rsub|k>|)>>|)>>|<cell|m\<geqslant\>1>>>>>
    </equation>

    and

    <\equation>
      z<rsub|n>=min<around*|(|lim<rsub|m\<rightarrow\>\<infty\>>Y<rsup|+><rsub|n,m><around*|(|t|)>,lim<rsub|m\<rightarrow\>\<infty\>>Y<rsup|-><rsub|n,m><around*|(|t|)>|)>
    </equation>

    which must exist because there is known to be an infinity of zeros on the
    critical line.\ 
  </lemma>

  <\proof>
    The only way <math|z<rsub|n>> would not exist is if all the roots
    <math|y<rsub|k>> were indifferent fixed-points<math|\<forall\>k\<gtr\>n>
    but that is impossible since there are no indifferent fixed-points of
    <math|Y<rsub|n,m><around*|(|t|)>> because for a fixed-point
    <math|y<rsub|k>> to be indifferent would require
    <math|tanh<around*|(|<frac|Z<around*|(|Y<rsub|n,m-1><around*|(|t|)>|)>|<around*|\||\<Omega\><around*|(|t|)>|\|>>|)>=1>
    which is only possible if <math|<around*|\||Z<around*|(|Y<rsub|n,m-1><around*|(|t|)>|)>|\|>=\<infty\>>
    for some <math|m\<in\>\<bbb-Z\><rsup|+>> and the <math|Z> function only
    takes on the value <math|\<infty\>> when <math|t=-<frac|i|2>> which
    corresponds to the pole at <math|\<zeta\><around*|(|1|)>> since
    <math|<frac|1|2>+i<around*|(|-<frac|i|2>|)>=1>.
  </proof>

  <\conjecture>
    The roots generated by the sequence <math|y<rsub|n>=lim<rsub|m\<rightarrow\>\<infty\>>Y<rsub|n,m><around*|(|t|)>>
    are simple
  </conjecture>

  <\conjecture>
    Let

    <\equation>
      c<rsub|n><around*|(|\<varepsilon\>|)>=<frac|Z<around*|(|max<rsub|t\<in\><around*|[|0,y<rsub|n>|]>><around*|{|Y<rsub|n+1,1><around*|(|t|)>\<geqslant\>t|}>+\<epsilon\>|)>-Z<around*|(|min<rsub|t\<in\><around*|[|y<rsub|n>,\<infty\>|]>><around*|{|Y<rsub|n+1,1><around*|(|t|)>\<leqslant\>t|}>-\<epsilon\>|)>|2\<varepsilon\>+max<rsub|t\<in\><around*|[|0,y<rsub|n>|]>><around*|{|Y<rsub|n+1,1><around*|(|t|)>\<geqslant\>t|}>-min<rsub|t\<in\><around*|[|y<rsub|n>,\<infty\>|]>><around*|{|Y<rsub|n+1,1><around*|(|t|)>\<leqslant\>t|}>>
    </equation>

    denote the Lipschitz constant in Formula <reference|lc> then it is always
    possible to choose a small enough positive <math|\<varepsilon\>> such
    that <math|0\<less\>c<rsub|n><around*|(|\<varepsilon\>|)>\<less\>1>.
  </conjecture>

  <section|Appendix>

  Let <math|\<zeta\><around*|(|t|)>> be the Riemann zeta function\ 

  <\equation>
    <tabular|<tformat|<table|<row|<cell|\<zeta\><around*|(|t|)>>|<cell|=<big|sum><rsub|n=1><rsup|\<infty\>>n<rsup|-s>>|<cell|\<forall\>Re<around*|(|s|)>\<gtr\>1>>|<row|<cell|>|<cell|=<around*|(|1-2<rsup|1-s>|)><big|sum><rsub|n=1><rsup|\<infty\>>n<rsup|-s><around*|(|-1|)><rsup|n-1>>|<cell|\<forall\>Re<around*|(|s|)>\<gtr\>0>>>>>
  </equation>

  and <math|\<vartheta\><around*|(|t|)>> be Riemann-Siegel vartheta function

  \;

  <\equation>
    \<vartheta\><around*|(|t|)>=-<frac|i|2><around*|(|ln
    \<Gamma\><around*|(|<frac|1|4>+<frac|i t|2>|)>-ln
    \<Gamma\><around*|(|<frac|1|4>-<frac|i
    t|2>|)>|)>-<frac|ln<around*|(|\<pi\>|)> t|2><label|vartheta>
  </equation>

  so that the Hardy <math|Z> function<cite|HardyZ> can be defined by

  <\equation>
    <tabular|<tformat|<table|<row|<cell|Z<around*|(|t|)>>|<cell|=e<rsup|i
    \<vartheta\><around*|(|t|)>> \<zeta\><around*|(|<frac|1|2>+i
    t|)>>>>>><label|Z>
  </equation>

  which is real-valued when <math|t> is real and satisfies the identity

  <\equation>
    \<zeta\><around*|(|t|)>=e<rsup|-i \<vartheta\><around*|(|<frac|i|2>-i
    t|)>>Z<around*|(|<frac|i|2>-i t|)><label|Zz>
  </equation>

  \;

  where <math|ln\<Gamma\><around*|(|z|)>> is the principal branch of the
  logarithm of the <math|\<Gamma\>> function defined by\ 

  <\equation>
    ln\<Gamma\><around*|(|z|)>=ln<around*|(|\<Gamma\><around*|(|z|)>|)>=<around*|(|z-1|)>!=<big|prod><rsub|k=1><rsup|z-1>k\<forall\>z\<in\>\<bbb-R\>\<gtr\>0
  </equation>

  which is analytically continued from the positive real axis when
  <math|z\<in\>\<bbb-C\>> is complex. Each of the points
  <math|z\<in\>\<bbb-Z\>=<around*|{|0,-1,-2,\<ldots\>|}>> is a singularity
  and a branch point so that the union of the branch cuts is the negative
  real axis. On the branch cuts, the values of
  <math|ln\<Gamma\><around*|(|z|)>> are determined by continuity from above.
  Let <math|S<around*|(|t|)>> denote the normalized argument of
  <math|\<zeta\><around*|(|t|)>> on the critical line\ 

  <\equation>
    <tabular|<tformat|<table|<row|<cell|S<around*|(|t|)>>|<cell|=\<pi\><rsup|-1>
    arg<around*|(|\<zeta\><around*|(|<frac|1|2>+i
    t|)>|)>>>|<row|<cell|>|<cell|=-<frac|i|2\<pi\>><around*|(|ln
    \<zeta\><around*|(|<frac|1|2>+i t|)>-ln\<zeta\><around*|(|<frac|1|2>-i
    t|)>|)>>>|<row|<cell|>|<cell|=<frac|1|\<pi\>>lim<rsub|\<varepsilon\>\<rightarrow\>0>Im<around*|(|ln\<zeta\><around*|(|<frac|1|2>+i
    t+\<varepsilon\>|)>|)>>>>>><label|S>
  </equation>

  <\definition>
    The <verbatim|critical line> is the line in the complex plane defined by
    <math|Re<around*|(|t|)>=<frac|1|2>>.
  </definition>

  <\definition>
    The <verbatim|critical strip> is the strip in the complex plane defined
    by <math|0\<less\>Re<around*|(|t|)>\<less\>1>.
  </definition>

  <\definition>
    A fixed-point <math|\<alpha\>> of a function <math|f<around*|(|x|)>> is a
    value <math|\<alpha\>> such that

    <\equation>
      f<around*|(|\<alpha\>|)>=\<alpha\>
    </equation>

    <cite-detail|raadstrom1953iteration|3.>
  </definition>

  <\definition>
    The multiplier <math|\<lambda\><rsub|f><around*|(|\<alpha\>|)>> of a
    fixed point <math|\<alpha\>> of a map <math|f<around*|(|x|)>> is equal to
    the derivative <math|<wide|f|\<dot\>><around*|(|\<alpha\>|)>> of the map
    evaluated at the point <math|\<alpha\>> which is the first term in the
    Taylor expansion at that point\ 

    <\equation>
      \<lambda\><rsub|f><around*|(|\<alpha\>|)>=<wide|f|\<dot\>><around*|(|\<alpha\>|)>
    </equation>

    If <math|<around*|\||\<lambda\><rsub|f><around*|(|\<alpha\>|)>|\|>\<less\>1>
    then <math|\<alpha\>> is a said to be an attractive fixed-point of
    <math|f<around*|(|x|)>>. If <math|<around*|\||\<lambda\><rsub|f><around*|(|\<alpha\>|)>|\|>=1>
    then <math|\<alpha\>> is an indifferent fixed-point of
    <math|f<around*|(|t|)>> also known as as neutral fixed-point, and if
    <math|<around*|\||\<lambda\><rsub|f><around*|(|\<alpha\>|)>\<gtr\>1|\|>>
    then <math|\<alpha\>> is a repelling fixed-pint of
    <math|f<around*|(|t|)>>. When <math|<around*|\||\<lambda\><rsub|f><around*|(|\<alpha\>|)>|\|>=0>
    the fixed-point <math|\<alpha\>> is said to be superattractive
    fixed-point of <math|f<around*|(|t|)>><cite-detail|raadstrom1953iteration|3.>
  </definition>

  <\lemma>
    (The Banach Fixed-Point Theorem)

    If <math|f<around*|(|x|)>> is a continuous function defined on
    <math|<around*|[|a,b|]>> and

    <\equation>
      f<around*|(|x|)>\<in\><around*|[|a,b|]>\<forall\>x\<in\><around*|[|a,b|]>
    </equation>

    and there exists some constant <math|0\<less\>c\<less\>1> such that

    <\equation>
      <text|<math|<frac|<around*|\||f<around*|(|x|)>-f<around*|(|y|)>|\|>|x-y>\<leqslant\>c
      >><label|lc>
    </equation>

    then <math|f<around*|(|x|)>> has a unique fixed-point
    <math|x\<in\><around*|[|a,b|]>> and the sequence
    <math|f<around*|(|x<rsub|0>|)>,f<around*|(|f<around*|(|x<rsub|0>|)>|)>,f<around*|(|f<around*|(|f<around*|(|x<rsub|0>|)>|)>|)>,\<ldots\>>
    converges to the unique fixed-point of <math|f<around*|(|x|)>> in the
    interval <math|<around*|[|a,b|]>>.
  </lemma>

  <\lemma>
    (Milnor's Lemma) Every <verbatim|simple root> of <math|f<around*|(|t|)>>
    is a super-attractive fixed-point of <math|N<rsub|f><around*|(|t|)>>
    since a superattractive fixed-point is one such that its multiplier
    <math|\<lambda\><rsub|f><around*|(|t|)>=0> so that its multiplicity is\ 

    <\equation>
      m<rsub|f><around*|(|t|)>=<frac|1|1-\<lambda\><rsub|N<rsub|f>><around*|(|t|)>>=<frac|1|1-0>=<frac|1|1>=1
    </equation>

    See <cite-detail|milnor2006dynamics|p.52>
  </lemma>

  <\proof>
    Let <math|\<alpha\>> be a root <math|Z<around*|(|\<alpha\>|)>=0> then the
    multiplier of its Newton map is <math|\<lambda\><rsub|N<rsub|Z>><around*|(|\<alpha\>|)>=><math|\<lambda\><rsub|f><around*|(|\<alpha\>|)>=<frac|Z<around*|(|\<alpha\>|)><wide|Z|\<ddot\>><around*|(|\<alpha\>|)>|<wide|Z|\<dot\>><around*|(|\<alpha\>|)><rsup|2>>=0
    > since <math|Z<around*|(|\<alpha\>|)>=0> the entire expression
    <math|<frac|Z<around*|(|\<alpha\>|)><wide|Z|\<ddot\>><around*|(|\<alpha\>|)>|<wide|Z|\<dot\>><around*|(|\<alpha\>|)>>>
    is equal to 0 since due to the ordering of operations the value of
    <math|<wide|Z|\<dot\>><around*|(|t|)>> or
    <math|<wide|Z|\<ddot\>><around*|(|t|)>> is never required to be known in
    order to know the value of <math|\<lambda\><rsub|N<rsub|Z>><around*|(|t|)>>
    when <math|Z<around*|(|t|)>=0>.<space|1em>If any term in the product is 0
    then the entire product takes the value 0. The multiplicity is related to
    the multiplier by <math|m<rsub|Z><around*|(|t|)>=<frac|1|1-\<lambda\><rsub|N<rsub|Z>><around*|(|t|)>>=1>
    and therefore simple. Since \ <math|m<rsub|Z><around*|(|t|)>=<frac|1|1-\<lambda\><rsub|N<rsub|Z>><around*|(|t|)>>\<forall\>\<lambda\><rsub|N<rsub|Z>><around*|(|t|)>\<neq\>1>then
    it is known that <math|\<lambda\><rsub|N<rsub|Z>><around*|(|t|)>=0> when
    <math|Z<around*|(|t<rsub|>|)>=0> \ therefore the point <math|\<alpha\>>
    is a superattractive fixed-point corresponding to a simple zero at
    <math|\<alpha\>>. Since we now know that
    <math|m<rsub|Z>=<around*|(|\<alpha\>|)>> and therefore the zero at
    <math|Z<around*|(|\<alpha\>|)>=0> is simple, we therefore know that the
    denominator <math|<wide|\<Zeta\>|\<dot\>><around*|(|t|)>> of the
    multiplier <math|\<lambda\><rsub|N<rsub|Z>><around*|(|t|)>> cannot vanish
    so that \ \ <math|<wide|\<Zeta\>|\<dot\>><around*|(|\<alpha\>|)>\<neq\>0>
    since that would imply that <math|\<alpha\>> is not a simple root, which
    would be a contradiction to the already established fact that
    <math|m<rsub|Z><around*|(|\<alpha\>|)>=1> when
    <math|Z<around*|(|\<alpha\>|)>=0>.
  </proof>

  <\definition>
    The <verbatim|multiplicity> <math|m<rsub|f><around*|(|t|)>> of a root
    <math|\<alpha\>> is a root <math|f<around*|(|\<alpha\>|)>=0> such that
    its Taylor expansion about the point <math|\<alpha\>> has the form

    <\equation>
      f<around*|(|*t|)>=c<around*|(|t-\<alpha\>|)><rsup|m<rsub|f><around*|(|t|)>>+<around*|(|higher
      order terms\<ldots\>|)>
    </equation>

    where <math|c\<neq\>0> and <math|m\<geqslant\>1>. The multiplicity of a
    root <math|t> is related to the multipler
    <math|\<lambda\><rsub|f><around*|(|t|)>> through the formula

    <\equation>
      m<rsub|f><around*|(|t|)>=<frac|1|1-\<lambda\><rsub|N<rsub|f>><around*|(|t|)>>
    </equation>
  </definition>

  where\ 

  <\equation>
    \<lambda\><rsub|N<rsub|f>><around*|(|t|)>=<frac|f<around*|(|t|)><wide|f|\<ddot\>><around*|(|t|)>|<wide|f|\<dot\>><around*|(|t|)><rsup|2>>
  </equation>

  \ is the first derivative of the Newton map of <math|f<around*|(|t|)>>

  <\equation>
    N<rsub|f><around*|(|t|)>=t-<frac|f<around*|(|t|)>|<wide|f|\<dot\>><around*|(|t|)>>
  </equation>

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

  <\definition>
    The Riemann-von-Mangoldt formula makes use of Cauchy's argument principle
    to count the number of zeros <verbatim|inside the critical strip>
    <math|0\<less\>Im<around*|(|\<rho\><rsub|n>|)>\<less\>t> where
    <math|\<zeta\><around*|(|\<sigma\>+i \<rho\><rsub|n>|)>> with
    <math|0\<less\>\<sigma\>\<less\>1>

    <\equation>
      N<around*|(|t|)>=<frac|t|2 \<pi\>>ln<around*|(|<frac|t|2 \<pi\>
      e>|)>+<frac|7|8>+S<around*|(|t|)>+O<around*|(|t<rsup|-1>|)>
    </equation>

    and this definition does not depend on the Riemann hypothesis(Conjecture
    <reference|RH>). This equation has exactly the same form as the
    asymptotic Equation <reference|ae>. <cite-detail|z0t|Equation 15>
  </definition>

  \;

  <\bibliography|bib|tm-plain|references.bib>
    <\bib-list|7>
      <bibitem*|1><label|bib-z0t>Guilherme Franca<localize| and >Andre
      LeClair. <newblock>Transcendental equations satisfied by the individual
      zeros of riemann zeta, dirichlet and modular l functions.
      <newblock><with|font-shape|italic|Communications in Number Theory and
      Physics>, 2015.<newblock>

      <bibitem*|2><label|bib-HardyZ>A.<nbsp>Ivi¢.
      <newblock><with|font-shape|italic|The Theory of Hardy's Z-Function>.
      <newblock>Cambridge Tracts in Mathematics. Cambridge University Press,
      2013.<newblock>

      <bibitem*|3><label|bib-Johansson2017arb>F.<nbsp>Johansson.
      <newblock>Arb: efficient arbitrary-precision midpoint-radius interval
      arithmetic. <newblock><with|font-shape|italic|IEEE Transactions on
      Computers>, 66:1281\U1292, 2017.<newblock>

      <bibitem*|4><label|bib-milnor2006dynamics>John Milnor.
      <newblock><with|font-shape|italic|Dynamics in One Complex Variable>.
      <newblock>Annals of Mathematics Studies 160. Princeton University
      Press, 2nd<localize| edition>, 2006.<newblock>

      <bibitem*|5><label|bib-zt>Andrew Odlyzko. <newblock>Tables of zeros of
      the riemann zeta function.<newblock>

      <bibitem*|6><label|bib-raadstrom1953iteration>Hans Rådström.
      <newblock>On the iteration of analytic functions.
      <newblock><with|font-shape|italic|Mathematica Scandinavica>,
      <localize|pages >85\U92, 1953.<newblock>

      <bibitem*|7><label|bib-ramachandra1995lectures>Kanakanahalli
      Ramachandra. <newblock><with|font-shape|italic|Lectures on the
      mean-value and omega-theorems for the Riemann zeta-function>,
      <localize|volume><nbsp>85. <newblock>Springer, 1995.<newblock>
    </bib-list>
  </bibliography>
</body>

<\initial>
  <\collection>
    <associate|page-type|letter>
  </collection>
</initial>

<\references>
  <\collection>
    <associate|N0|<tuple|8|2>>
    <associate|RH|<tuple|33|9>>
    <associate|S|<tuple|27|7>>
    <associate|Z|<tuple|24|7>>
    <associate|Zz|<tuple|25|7>>
    <associate|ae|<tuple|4|2>>
    <associate|auto-1|<tuple|1|1>>
    <associate|auto-10|<tuple|2|7>>
    <associate|auto-11|<tuple|38|9>>
    <associate|auto-2|<tuple|1.1|1>>
    <associate|auto-3|<tuple|1|2>>
    <associate|auto-4|<tuple|1.1.1|3>>
    <associate|auto-5|<tuple|1.1.2|4>>
    <associate|auto-6|<tuple|1.1.3|4>>
    <associate|auto-7|<tuple|2|4>>
    <associate|auto-8|<tuple|3|5>>
    <associate|auto-9|<tuple|1.1.4|5>>
    <associate|bib-HardyZ|<tuple|2|9>>
    <associate|bib-Johansson2017arb|<tuple|3|9>>
    <associate|bib-milnor2006dynamics|<tuple|4|9>>
    <associate|bib-raadstrom1953iteration|<tuple|6|9>>
    <associate|bib-ramachandra1995lectures|<tuple|7|9>>
    <associate|bib-z0t|<tuple|1|9>>
    <associate|bib-zt|<tuple|5|9>>
    <associate|cm|<tuple|16|5>>
    <associate|ee|<tuple|1|1>>
    <associate|fl|<tuple|6|3>>
    <associate|lc|<tuple|31|8>>
    <associate|le|<tuple|4|2>>
    <associate|ord|<tuple|5|2>>
    <associate|p1|<tuple|17|5>>
    <associate|pc|<tuple|21|6>>
    <associate|peven|<tuple|14|4>>
    <associate|podd|<tuple|13|4>>
    <associate|vartheta|<tuple|23|7>>
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

      z0t

      ramachandra1995lectures

      Johansson2017arb

      zt

      HardyZ

      raadstrom1953iteration

      raadstrom1953iteration

      milnor2006dynamics

      z0t
    </associate>
    <\associate|figure>
      <tuple|normal|<surround|<hidden|<tuple>>||The functions
      <with|mode|<quote|math>|\<vartheta\><around*|(|y<rsub|n>|)>+S<around*|(|y<rsub|n>|)>-<around*|(|n-<frac|3|2>|)>\<pi\>>
      and <with|mode|<quote|math>|<wide|\<vartheta\>|~><around*|(|y<rsub|n>|)>+S<around*|(|y<rsub|n>|)>-<around*|(|n-<frac|3|2>|)>\<pi\>>
      for <with|mode|<quote|math>|n=1,2> with the zeros at
      <with|mode|<quote|math>|y<rsub|1>> and
      <with|mode|<quote|math>|y<rsub|2>> marked with vertical lines.
      >|<pageref|auto-3>>

      <tuple|normal|<surround|<hidden|<tuple>>||<with|mode|<quote|math>|The>
      functions which are subtracted or added to <with|mode|<quote|math>|t>
      to get <with|mode|<quote|math>|Y<rsub|1><around*|(|t|)>,Y<rsub|2><around*|(|t|)>,Y<rsub|3><around*|(|t|)>,Y<rsub|4><around*|(|t|)>>.
      When <with|mode|<quote|math>|n> is odd
      <with|mode|<quote|math>|cos<around*|(|\<pi\>n|)>=-1> so that the the
      value is subtracted from <with|mode|<quote|math>|t>, when
      <with|mode|<quote|math>|n> is even <with|mode|<quote|math>|cos<around*|(|2\<pi\>|)>=1>
      so it is added. It is plain to see that the curves
      <with|mode|<quote|math>|><with|mode|<quote|math>|tanh<around*|(|<frac|Z<around*|(|t|)>|\<Omega\><around*|(|t|)><big|prod><rsub|k=1><rsup|n-1>tanh<around*|(|t-y<rsub|k>|)>>|)>>
      do not cross the zero axis for any <with|mode|<quote|math>|t\<less\>y<rsub|n>>
      >|<pageref|auto-7>>

      <tuple|normal|<surround|<hidden|<tuple>>||Multipler<with|mode|<quote|math>|>
      of the maps <with|mode|<quote|math>|Y<rsub|1><around*|(|t|)>> and
      <with|mode|<quote|math>|Y<rsub|3><around*|(|t|)>>>|<pageref|auto-8>>
    </associate>
    <\associate|toc>
      <vspace*|1fn><with|font-series|<quote|bold>|math-font-series|<quote|bold>|1<space|2spc>Introduction>
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-1><vspace|0.5fn>

      <with|par-left|<quote|1tab>|1.1<space|2spc>Transcendental Equations
      Satisifed By The Nontrivial Riemann Zeros
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-2>>

      <with|par-left|<quote|2tab>|1.1.1<space|2spc>An Iteration Function
      Which Successively Removes Roots <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-4>>

      <with|par-left|<quote|2tab>|1.1.2<space|2spc>Indifferent Fixed-Points
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-5>>

      <with|par-left|<quote|2tab>|1.1.3<space|2spc>Alternating Attractive and
      Repulsive Fixed-Points <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-6>>

      <with|par-left|<quote|2tab>|1.1.4<space|2spc>Sequential Convergence to
      the Nearest Fixed-Points <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-9>>

      <vspace*|1fn><with|font-series|<quote|bold>|math-font-series|<quote|bold>|2<space|2spc>Appendix>
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-10><vspace|0.5fn>

      <vspace*|1fn><with|font-series|<quote|bold>|math-font-series|<quote|bold>|Bibliography>
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-11><vspace|0.5fn>
    </associate>
  </collection>
</auxiliary>