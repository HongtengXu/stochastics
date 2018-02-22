<TeXmacs|1.99.5>

<style|article>

<\body>
  \;

  <doc-data|<doc-title|Superattractive Fixed-points of a Newton-like Mappping
  of the Hardy Z Function>|<doc-author|>|<doc-author|<\author-data|<author-name|Stephen
  Crowley>>
    \;
  <|author-data|<\author-affiliation>
    <date>
  </author-affiliation>>
    \;

    \;
  <|author-data>
    \;

    \;

    \;
  </author-data>>>

  There is shown to exist a unique solutions to the LeClaire-Fran�a exact
  equation for the first <math|98,020> of the first 100,000 zeros of the
  Hardy Z Function via the construction of Cauchy sequences whose
  accumulation points are guaranteed via an application of the
  Newton-Kantorovich theorem applied to a Newton-like map.

  <\footnote>
    stephencrowley214 at gmail dot com
  </footnote>

  <\table-of-contents|toc>
    <vspace*|1fn><with|font-series|bold|math-font-series|bold|1<space|2spc>Derivations>
    <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
    <no-break><pageref|auto-1><vspace|0.5fn>

    <with|par-left|1tab|1.1<space|2spc>Preliminaries
    <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
    <no-break><pageref|auto-2>>

    <with|par-left|1tab|1.2<space|2spc>The Newton Map
    <with|mode|math|N<rsub|Z><around*|(|t|)>> of
    <with|mode|math|Z<around*|(|t|)>> <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
    <no-break><pageref|auto-3>>

    <with|par-left|2tab|1.2.1<space|2spc>The LeClair-Fran�a Formula and
    Stirling's Approximation of <with|mode|math|ln\<Gamma\>>
    <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
    <no-break><pageref|auto-4>>

    <with|par-left|2tab|1.2.2<space|2spc>A Newton-like Map
    <with|mode|math|M<rsub|Z><around*|(|t|)>> Which Is Holomorphic In The
    Critical Strip <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
    <no-break><pageref|auto-6>>

    <vspace*|1fn><with|font-series|bold|math-font-series|bold|2<space|2spc>Newton's
    Method and the Hardy Z Function> <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
    <no-break><pageref|auto-7><vspace|0.5fn>

    <with|par-left|1tab|2.1<space|2spc>Cauchy Sequences and Newton's Method
    <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
    <no-break><pageref|auto-8>>

    <with|par-left|1tab|2.2<space|2spc>Semi-Local Convergence of the Relaxed
    Newton's Method <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
    <no-break><pageref|auto-10>>

    <vspace*|1fn><with|font-series|bold|math-font-series|bold|3<space|2spc>Appendix>
    <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
    <no-break><pageref|auto-11><vspace|0.5fn>

    <with|par-left|1tab|3.1<space|2spc>Complex Dynamics
    <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
    <no-break><pageref|auto-12>>

    <with|par-left|2tab|3.1.1<space|2spc>Holomorphic Index
    <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
    <no-break><pageref|auto-13>>

    <with|par-left|1tab|3.2<space|2spc>Convergence of Newton's Method,
    Lipschitz Functions, and The Mean Value Theorem
    <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
    <no-break><pageref|auto-14>>

    <with|par-left|2tab|3.2.1<space|2spc>Lehmer's Phenomena
    <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
    <no-break><pageref|auto-15>>

    <vspace*|1fn><with|font-series|bold|math-font-series|bold|Bibliography>
    <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
    <no-break><pageref|auto-16><vspace|0.5fn>
  </table-of-contents>

  <section|Derivations>

  <subsection|Preliminaries>

  Let <math|\<zeta\><around*|(|t|)>> be the Riemann zeta function\ 

  <\equation>
    <tabular|<tformat|<table|<row|<cell|\<zeta\><around*|(|t|)>>|<cell|=<big|sum><rsub|n=1><rsup|\<infty\>>n<rsup|-s>>|<cell|\<forall\>Re<around*|(|s|)>\<gtr\>1>>|<row|<cell|>|<cell|=<around*|(|1-2<rsup|1-s>|)><big|sum><rsub|n=1><rsup|\<infty\>>n<rsup|-s><around*|(|-1|)><rsup|n-1>>|<cell|\<forall\>Re<around*|(|s|)>\<gtr\>0>>>>>
  </equation>

  and <math|\<vartheta\><around*|(|t|)>> be Riemann-Siegel vartheta function
  <math|\<vartheta\><around*|(|t|)>>

  \;

  <\equation>
    \<vartheta\><around*|(|t|)>=-<frac|i|2><around*|(|ln
    \<Gamma\><around*|(|<frac|1|4>+<frac|i t|2>|)>-ln
    \<Gamma\><around*|(|<frac|1|4>-<frac|i
    t|2>|)>|)>-<frac|ln<around*|(|\<pi\>|)> t|2>
  </equation>

  The exact equation for the <math|n>-th Riemann zero <math|t<rsub|n>> is
  given by <cite-detail|z0t|Equation 20>

  <\equation>
    \<vartheta\><around*|(|t<rsub|n>|)>+S<around*|(|t<rsub|n>|)>=<around*|(|n-<frac|3|2>|)>\<pi\>
  </equation>

  therefore the normalized argument of \<#3B6\> is given by\ 

  <\equation>
    <tabular|<tformat|<table|<row|<cell|S<around*|(|t|)>>|<cell|=\<pi\><rsup|-1>
    arg<around*|(|\<zeta\><around*|(|<frac|1|2>+i
    t|)>|)>>>|<row|<cell|>|<cell|=-<frac|i|2\<pi\>><around*|(|ln
    \<zeta\><around*|(|<frac|1|2>+i t|)>-ln\<zeta\><around*|(|<frac|1|2>-i
    t|)>|)>>>|<row|<cell|>|<cell|=<frac|1|\<pi\>>lim<rsub|\<varepsilon\>\<rightarrow\>0>Im<around*|(|ln\<zeta\><around*|(|<frac|1|2>+i
    t+\<varepsilon\>|)>|)>>>>>><label|S>
  </equation>

  which is discontinuous at the the <math|n>-th zero where it is equal to

  <\equation>
    S<rsub|n>=S<around*|(|y<rsub|n>|)>=<around*|(|n-<frac|3|2>|)>\<pi\>-\<vartheta\><around*|(|y<rsub|n>|)>
  </equation>

  which is approximated by

  <\equation>
    S<rsub|n>=S<around*|(|y<rsub|n>|)>=<around*|(|n-<frac|3|2>|)>\<pi\>-<around*|(|<wide|\<vartheta\>|~><around*|(|<wide|y|~><rsub|n>|)>+\<Delta\><around*|(||)>|)>=S<around*|(|y<rsub|n>|)>+\<Delta\><around*|(|y<rsub|n>|)>
  </equation>

  where\ 

  <\equation>
    <tabular*|<tformat|<cwith|1|-1|2|2|cell-halign|l>|<table|<row|<cell|\<Delta\><around*|(|t|)>>|<cell|=\<vartheta\><around*|(|t|)>-<wide|\<vartheta\>|~><around*|(|t|)>=O<around*|(|<frac|1|t>|)>>>|<row|<cell|>|<cell|=<frac|t|4>log<around*|(|1+<frac|1|4
    t<rsup|2>>|)>+<frac|1|4>arctan<around*|(|<frac|1|2
    t>|)>+<frac|t|2><big|int><rsub|0><rsup|\<infty\>><frac|\<lfloor\>u\<rfloor\>-<frac|1|2>|<around*|(|u+<frac|1|4>|)><rsup|2>+<around*|(|<frac|t|2>|)><rsup|2>>\<mathd\>u>>|<row|<cell|>|<cell|=<frac|t|4>log<around*|(|1+<frac|1|4
    t<rsup|2>>|)>+<frac|1|4>arctan<around*|(|<frac|1|2
    t>|)>+<frac|t|2><big|sum><rsub|n=0><rsup|\<infty\>>-<frac|<around*|(|2n-1|)><around*|(|*arctan<around*|(|<frac|4n+1|2t>|)>-arctan<around*|(|<frac|4n+5|2t>|)>|)>|t>>>>>><label|Dt>
  </equation>

  where <math|\<lfloor\>u\<rfloor\>> is the floor function which truncates
  the fractional part of its argument <cite-detail|HardyZ|Lemma 5.1> and the
  integral and has been re-expressed as an infinite sum since

  <\equation>
    <big|int><rsub|n><rsup|n+1><frac|\<lfloor\>u\<rfloor\>-<frac|1|2>|<around*|(|u+<frac|1|4>|)><rsup|2>+<around*|(|<frac|t|2>|)><rsup|2>>\<mathd\>u=-<frac|<around*|(|2n-1|)><around*|(|*arctan<around*|(|<frac|4n+1|2t>|)>-arctan<around*|(|<frac|4n+5|2t>|)>|)>|t>
  </equation>

  which has a removable singularity at <math|t=0> where it is equalt to the
  limit

  <\equation>
    lim<rsub|t\<rightarrow\>0><big|int><rsub|n><rsup|n+1><frac|\<lfloor\>u\<rfloor\>-<frac|1|2>|<around*|(|u+<frac|1|4>|)><rsup|2>+<around*|(|<frac|t|2>|)><rsup|2>>\<mathd\>u=<frac|16n-8|16n<rsup|2>+24n+5>=<frac|8<around*|(|2n-1|)>|<around*|(|4n+1|)><around*|(|4n+5|)>>
  </equation>

  \;

  <\definition>
    The approximate equation for the <math|n>-th Riemann zero is\ 

    <\equation>
      <wide|\<vartheta\>|~><around*|(|<wide|y|~><rsub|n>|)>=<around*|(|n-<frac|3|2>|)>\<pi\><label|arz>
    </equation>

    which is solved by

    <\equation>
      <wide|y|~><rsub|n>=t<rsub|n><rsup|\<ast\>*\<ast\>>=<frac|2 \<pi\>
      <around*|(|n-<frac|11|8>|)>|W<around*|(|<frac|n-<frac|11|8>|e>|)>>
    </equation>

    where <math|W<around*|(|x|)>> is the Lambert W function defined as the
    solution to the equation <math|W<around*|(|x|)>e<rsup|W<around*|(|x|)>>=x>.<cite|z0t>
    The notation <math|t<rsub|n><rsup|\<ast\>\<ast\>>> is the notation used
    in <cite|marca2009mean> where this exact same function appeared. The
    approximate Gram points given by the exact solution of the first-order
    Stirling expansion of <math|\<vartheta\><around*|(|t|)>> in terms of the
    <math|LambertW> function (which is itself-defined in terms of a certain
    fixed-point iteration) take values that are very close to the true Gram
    points. In fact, this exact solution of the approximate equation \ gives
    an accuracy of <math|2.2\<times\>10<rsup|-3>> for <math|n=0> decreasing
    to <math|3.5\<times\>10<rsup|-4>> by <math|n=10>. Let the approximate
    Riemann-Siegel vartheta function be defined

    <\equation>
      <wide|\<vartheta\>|~><around*|(|t|)>=<frac|t |2>ln<around*|(|<frac|t|2
      \<pi\> e>|)>-<frac|\<pi\>|8>
    </equation>

    which by definition

    <\equation>
      \<vartheta\><around*|(|t|)>=<wide|\<vartheta\>|~><around*|(|t|)>+\<Delta\><around*|(|t|)>
    </equation>

    and <math|\<Delta\><around*|(|t|)>> is conjectured to be
    <math|O<around*|(|1|)>> due to compelling evidence offered in <cite|SO1>
    where it is also hypothesiszed that the suitably normalized distribution
    of <math|\<delta\>y<rsub|n>> is a Gaussian distribution with mean 0 and
    variance<math|<sqrt|<frac|\<pi\>|32>>>.<math|>
  </definition>

  The Hardy <math|Z> function<cite|HardyZ> can then be written as

  <\equation>
    <tabular|<tformat|<table|<row|<cell|Z<around*|(|t|)>>|<cell|=e<rsup|i
    \<vartheta\><around*|(|t|)>> \<zeta\><around*|(|<frac|1|2>+i
    t|)>>>>>><label|Z>
  </equation>

  which can be mapped isometrically back to the <math|\<zeta\>> function\ 

  <\equation>
    \<zeta\><around*|(|t|)>=e<rsup|-i \<vartheta\><around*|(|<frac|i|2>-i
    t|)>>Z<around*|(|<frac|i|2>-i t|)><label|Zz>
  </equation>

  due to the isometry\ 

  <\equation>
    t=<frac|1|2><rsub|>+i<around*|(|<frac|i|2>-i t|)>
  </equation>

  of the Mobius transforms <math|f<around*|(|t|)>=<frac|a t+b|c t+d>> with

  <\equation>
    <matrix|<tformat|<table|<row|<cell|a>|<cell|b>>|<row|<cell|c>|<cell|d>>>>>=<matrix|<tformat|<table|<row|<cell|-i>|<cell|<frac|i|2>>>|<row|<cell|0>|<cell|1>>>>>
    and its inverse <matrix|<tformat|<table|<row|<cell|a>|<cell|b>>|<row|<cell|c>|<cell|d>>>>><rsup|-1>=<matrix|<tformat|<table|<row|<cell|i>|<cell|<frac|1|2>>>|<row|<cell|0>|<cell|1>>>>><label|zzi>
  </equation>

  making possible the Riemann-Siegel-Hardy correspondence. The B�cklund
  counting formula gives the exact number of zeros on the critical strip
  <math|>up to level <math|t>, not just on the criticial line
  <math|Re<around*|(|t|)>=<frac|1|2>>, <cite-detail|borwein2008riemann|3.2>

  <\equation>
    <tabular|<tformat|<table|<row|<cell|N<around*|(|t|)>=>|<cell|#<around*|{|\<zeta\><around*|(|x+i
    y|)>=0:0\<leqslant\>x\<leqslant\>1,0\<leqslant\>y\<leqslant\>t|}>=<around|\<langle\>|N<around*|(|t|)>|\<rangle\>>+S<around*|(|t|)>>>>>><label|N>
  </equation>

  where <math|<around|\<langle\>|N<around*|(|t|)>|\<rangle\>>> is the smooth
  part of the counting function\ 

  <\equation>
    <tabular|<tformat|<table|<row|<cell|<around|\<langle\>|N<around*|(|t|)>|\<rangle\>>=\<pi\><rsup|-1>\<vartheta\><around*|(|t|)>+1>>>>>
  </equation>

  <cite|francca2014theory> The relationship between the functions
  <math|N<around*|(|t|)>>, <math|S<around*|(|t|)>>, and
  <math|Z<around*|(|t|)>> is demonstrated by\ 

  <\equation>
    ln \<zeta\><around*|(|<frac|1|2><rsub|>+i
    t|)>=ln<around*|\||Z<around*|(|t|)>|\|>+i\<pi\>S<around*|(|t|)>
  </equation>

  These formulas are true independent of the Riemann hypothesis which posits
  that all complex zeros of <math|\<zeta\><around*|(|s+i t|)>> have real part
  <math|s=<frac|1|2>>. <cite-detail|HardyZ|Corrollary 1.8 p.13>

  <subsection|The Newton Map <math|N<rsub|Z><around*|(|t|)>> of
  <math|Z<around*|(|t|)>>>

  Let the new Newton map <cite-detail|bergweiler1993iteration|6.1><cite|holomorphiczetaflow>
  of <math|Z<around*|(|t|)>> be defined by the meromorphic function

  <\equation>
    <tabular|<tformat|<table|<row|<cell|N<rsub|Z><around*|(|t|)>>|<cell|=t-<frac|Z<around*|(|t|)>|<wide|Z|\<dot\>><around*|(|t|)>>>>|<row|<cell|>|<cell|=t+<frac|i
    \<zeta\><around*|(|<frac|1|2>+i t|)>|<wide|\<vartheta\>|\<dot\>><around*|(|t|)>
    \<zeta\><around*|(|<frac|1|2>+i t|)>+<wide|\<zeta\>|\<dot\>><around*|(|<frac|1|2>+i
    t|)>>>>>>><label|ZNewtonFlow>
  </equation>

  which has poles at the zeros of <math|<wide|Z|\<dot\>><around*|(|s|)>.>

  <\proposition>
    The Newton map <math|N<rsub|Z><around*|(|t|)>> of <math|Z<around*|(|t|)>>
    is maximally flat in a neighborhood of its superattractive fixed points
    which are separated by poles which repel trajectories away from the
    points of maximum curvature between the fixed points and towards those
    ultimately leading back to some point of minimal curvature.
  </proposition>

  <subsubsection|The LeClair-Fran�a Formula and Stirling's Approximation of
  <math|ln\<Gamma\>>>

  <\definition>
    The exact equation for the Riemann Zeros is

    <\equation>
      \<vartheta\><around*|(|y<rsub|n>|)>+S<around*|(|y<rsub|n>|)>=<around*|(|n-<frac|3|2>|)>\<pi\><label|fle>
    </equation>
  </definition>

  <big-figure|<image|varThetaArgRelationship.png|555px|271px||>|<math|S<around*|(|t|)>>
  and <math|<around*|{|<around*|(|n-<frac|3|2>|)>\<pi\>-\<vartheta\><around*|(|t|)>:n=1\<ldots\>8|}>>>

  TODO: typeset the equations

  <\remark>
    Convergence of the relaxed or unrelaxed iteration of the Newton's is
    assured by having a coeffecient <math|h<rsub|n>\<leqslant\>0.5> however
    it does not necessarily to the nearest zero <math|y<rsub|n>>
    \ corresponding to the initial approximation zero
    <math|<wide|y|~><rsub|n>> and instead in many instances converges to a
    zero several indices away from the matching zero <math|y<rsub|n>> and
    gets attracted to an accumulation point in a neighboring basin. A slight
    modification to <math|N<rsub|Z>> is introduced based on the hyperbolic
    tangent function in the next section which has a much wider Fatou domains
    on which the Newton map iterates form normal families
  </remark>

  <subsubsection|A Newton-like Map <math|M<rsub|Z><around*|(|t|)>> Which Is
  Holomorphic In The Critical Strip>

  <\definition>
    A function between normed spaces with the property that the distance
    between function values is bounded by a constant multiple of the distance
    between the arguments is known as a <samp|Lipschitz function>. If the
    function satisfies the Lipschitz condition that\ 

    <\equation>
      <around*|\<\|\|\>|f<around*|(|x|)>-f<around*|(|y|)>|\<\|\|\>>\<leqslant\>L<around*|\<\|\|\>|x-y|\<\|\|\>>\<forall\>x,y\<in\>A
    </equation>

    where possibly <math|A> is a single point
    <math|A=<around*|{|x<rsub|0>|}>> then <math|f<around*|(|x|)>> is said to
    be <math|k>-Lipschitz on <math|A> or at <math|x<rsub|0>>. When <math|k=1>
    the function is a <verbatim|non-expansive mapping>, and is a
    <strong|contraction mapping> when <math|k\<less\>1>. <cite|maple>
  </definition>

  <\definition>
    Let the modified Newton map <cite-detail|bergweiler1993iteration|6.1><cite|holomorphiczetaflow>
    of <math|Z<around*|(|t|)>> be defined by the meromorphic function

    <\equation>
      <tabular|<tformat|<table|<row|<cell|M<rsub|Z><around*|(|t|)><rsub|h>>|<cell|=t-h\<cdot\>tanh<around*|(|<frac|Z<around*|(|t|)>|<wide|Z|\<dot\>><around*|(|t|)>>|)>>>|<row|<cell|>|<cell|=t+h\<cdot\>tanh<around*|(|<frac|i
      \<zeta\><around*|(|<frac|1|2>+i t|)>|<wide|\<vartheta\>|\<dot\>><around*|(|t|)>
      \<zeta\><around*|(|<frac|1|2>+i t|)>+<wide|\<zeta\>|\<dot\>><around*|(|<frac|1|2>+i
      t|)>>|)>>>>>><label|ZNewtonFlow>
    </equation>

    where the hypebolic tangent function <math|tanh<around*|(|z|)>=<frac|e<rsup|z>
    e<rsup|z>-1|e<rsup|z> e<rsup|z>+1>> maps
    <math|<around*|(|-\<infty\>,\<infty\>|)>\<rightarrow\><around*|(|-1,1|)>>
    and the parameter <math|h> is the relaxation parameter. The <math|tanh>
    function has an infinite number of simple poles with residue 1 at
    <math|<frac|i|2>\<pi\><around*|(|2 n+1|)>> for all integers <math|n> and
    is also holomorphic when <math|<around*|\||Im<around*|(|z|)>|\|>\<less\><frac|\<pi\>|2>>,
    that is, in the strip <math|Im<around*|(|z|)>\<in\><around*|(|-<frac|1|2>,<frac|1|2>|)>>,
    that is, there are no poles in this region.\ 
  </definition>

  <\definition>
    A function <with|font-shape|italic|f> that is Fr�chet differentiable for
    any point of <with|font-shape|italic|U> is said to be C<rsup|1> if the
    function

    <\equation>
      \<mathD\>f=U\<rightarrow\>B<around*|(|V,W|)>;x\<mapsto\>\<mathD\>f<around*|(|x|)>
    </equation>

    is continuous.
  </definition>

  <\corollary>
    The hyperbolic tangent function is continuous on
    <math|<wide|\<bbb-R\>|\<bar\>>=<around*|(|-\<infty\>,\<infty\>|)>> and
    thereore Fr�chet differentiable on \ <math|<wide|\<bbb-R\>|\<bar\>>=<around*|(|-\<infty\>,\<infty\>|)>>.
  </corollary>

  <\note>
    The function <math|M<rsub|Z><around*|(|t|)><rsub|h>> is discontinuous on
    a zero of <math|<wide|Z|\<dot\>><around*|(|t|)>> where it equals
    <math|\<pm\>1> and jumps by <math|\<pm\>2> and has the average value of
    the left and right limits approaching the discontinuity being equal to 0.
    \ The zeros of <math|<wide|Z|\<dot\>><around*|(|t|)>> are points of
    discontinuty with finite jumps of magnitude 2 where the function becomes
    non-expansive since the Lipschitz constant at these points approaches the
    limiting range of the hyperbolic tangent at <math|\<pm\>1> which has
    absolute value <math|1> approaching from either the left or right, at all
    other points of the <math|n>-th Fatou domain the function
    <math|M<rsub|Z><around*|(|t|)>> is a contraction mapping meaning that its
    Lipschitz constant is strictly less than one and converges smoothly to
    zero precisely at a zero of <math|Z<around*|(|t|)>>. The contraction
    mapping properties of the hyperbolic tangent function are proved in the
    case of the the real line in <cite|krcmar2000global> where it is shown
    that the fixed-point iteration converges at a linear rate. \ 
  </note>

  <section|Newton's Method and the Hardy Z Function>

  <subsection|Cauchy Sequences and Newton's Method>

  \;

  <\example>
    The first point(smallest value of <math|n>) where Newton's method fails
    to converge is\ 

    <\equation>
      <tabular*|<tformat|<cwith|1|-1|2|2|cell-halign|l>|<cwith|2|2|2|2|cell-halign|l>|<cwith|3|3|2|2|cell-halign|l>|<table|<row|<cell|lim<rsub|k\<rightarrow\>\<infty\>>M<rsup|\<circ\>k><rsub|Z><around*|(|<wide|y|~><rsub|1443>|)><rsub|1>>|<cell|=1918.786499\<ldots\>>>|<row|<cell|lim<rsub|k\<rightarrow\>\<infty\>>M<rsup|\<circ\>k><rsub|Z><around*|(|<wide|y|~><rsub|1444>|)><rsub|1>>|<cell|=1918.786499\<ldots\>>>|<row|<cell|lim<rsub|k\<rightarrow\>\<infty\>>M<rsup|\<circ\>k><rsub|Z><around*|(|<wide|y|~><rsub|1444>|)><rsub|0.8>>|<cell|=1919.207779\<ldots\>>>>>>
    </equation>
  </example>

  \;

  <\remark>
    The lower the value of <math|h> required to prevent the trajectory from
    leaving its immediate basin, the more iterations required to converge
    since each iteration is having smaller impact. \ If a point in the Cauchy
    sequence lands precisely on a zero of
    <math|<wide|Z|\<dot\>><around*|(|s|)>> then the value is actually 0 there
    since <math|-1> and <math|+1> has a mean value of 0.\ 
  </remark>

  <\example>
    There are values of <math|n> which do not converge no matter how small
    the relaxation factor is taken. This occurs when the starting point given
    by the Lambert W approximization zero closed-form expression is evaluated
    for <math|n=6400> where it just to the left of the boundary determined by
    the zero of <math|Z<rprime|'>> just to the right of it

    The smallest index <math|n> for which\ 

    <\equation>
      lim<rsub|m\<rightarrow\>\<infty\>>M<rsup|\<circ\>m><rsub|Z><around*|(|<wide|y|~><rsup|<around*|(|-|)>><rsub|n>|)>\<neq\>y<rsup|<around*|(|-|)>><rsub|n>
    </equation>

    is <math|n=1444> where

    <\equation>
      lim<rsub|m\<rightarrow\>\<infty\>>M<rsup|\<circ\>m><rsub|Z><around*|(|<wide|y|~><rsup|<around*|(|-|)>><rsub|1444>|)>=y<rsup|<around*|(|-|)>><rsub|1443>\<neq\>y<rsup|<around*|(|-|)>><rsub|1444>
    </equation>

    And the 21-st point\ 

    <\equation>
      lim<rsub|m\<rightarrow\>\<infty\>>M<rsup|\<circ\>m><rsub|Z><around*|(|<wide|y|~><rsup|<around*|(|-|)>><rsub|6400>|)>=y<rsup|<around*|(|-|)>><rsub|6399
      >\<neq\>y<rsup|<around*|(|-|)>><rsub|6400>
    </equation>

    <big-figure|<image|2ndDivergentPoint.eps|555px|555px||><image|WrongSide6400.png|333px|555px||>|<math|<around*|{|M<rsub|Z><around*|(|t|)>:t\<in\>6727\<ldots\>6728|}>>
    near <math|t=y<rsup|<around*|(|-|)>><rsub|6400>=6727.861353\<ldots\>>>

    \;
  </example>

  The first few values \ <math|n> for which
  <math|t\<rightarrow\>t-tanh<around*|(|<frac|Z<around*|(|<wide|y|~><rsub|n><rsup|<around*|(|-|)>>|)>|<wide|Z|\<dot\>><around*|(|<wide|y|~><rsub|n><rsup|<around*|(|-|)>>|)>>|)>>
  does not converge to <math|y<rsup|<around*|(|-|)>><rsub|n>> is

  <\equation>
    n=<around*|{|1444,2041,2813,3359,3474,3648,3841,3855,4330,4408,4527,5106,\<ldots\>|}>
  </equation>

  and it has been calculated that there are 1,980 of these points up to
  <math|n=100,000> which is 98.02% of the integers <math|n> up to that level.

  <\theorem>
    The LeClair-Fran�a Theorem. If there is a unique solution to (138) for
    each <math|n> then the zeros can be counted along the critical line since
    they are enumerated by the integer n.
    <cite-detail|francca2014theory|VII.B>\ 
  </theorem>

  \;

  <subsection|Semi-Local Convergence of the Relaxed Newton's Method>

  In <cite-detail|argyros2014convergence|3.> semi-local convergence theorems
  for the relaxed Newton's method are used to find estimates on the radii of
  the spheres of convergence surrounding a given starting point
  <math|x<rsub|o>>. Local convergence theorems give estimates of the radii of
  the attractors (the convergence balls which are subsets of the Fatou sets)
  surrounding the precisely known location of a root. Semi-local theorems
  give estimates of the convergence radii centered around the initial points
  of the sequence rather than the accumulation point. Semi-local convergence
  theorems are thus more applicible to the situation here where the initial
  points are given by the <math|<wide|y|~><rsub|n>> exact Lambert-W function
  solutions to the Stirling approximation equation and the goal is to show
  that the ball is large enough to always contain the relaxed Newton iterates
  given a small enough step size <math|h>).\ 

  <section|Appendix>

  <subsection|Complex Dynamics>

  <\definition>
    If <math|f> is a complex function defined in <math|\<Omega\>> and the
    derivative of <math|f> at <math|z<rsub|0>> defined by

    <\equation>
      <wide|f|\<dot\>><around*|(|z<rsub|0>|)>=lim<rsub|z\<rightarrow\>z<rsub|0>><frac|f<around*|(|z|)>-f<around*|(|z<rsub|0>|)>|z-z<rsub|0>>\<forall\>z<rsub|0>\<in\>\<Omega\>
    </equation>

    exists then <math|f> is said to be holomorphic or analytic in
    <math|\<Omega\>>. The class of all holomorphic functions is denoted
    <math|H<around*|(|\<Omega\>|)>. > A function <math|f> is known as an
    entire function when <math|\<Omega\>> is the entire complex plane
    <math|\<bbb-C\>>. <cite-detail|Rudin2006|Definition 10.2>
  </definition>

  <\definition>
    The <verbatim|multiplier> of <math|F> at the fixed-point <math|\<alpha\>>
    is the derivative of the Newton map <math|N<rsub|F><around*|(|t|)>>
    evaluated at \ <math|\<alpha\>>, <math|N<rsub|f><around*|(|t|)><mid|\|><rsub|t=\<alpha\>>>
    which can be written

    <\equation>
      <wide|N|\<dot\>><rsub|F><around*|(|t|)>=<frac|F<around*|(|t|)><wide|F|\<ddot\>><around*|(|t|)>|<wide|F|\<dot\>><around*|(|t|)><rsup|2>>
    </equation>
  </definition>

  <\definition>
    A point <math|z<rsub|0>\<in\>\<bbb-C\>> is called a periodic point of
    <math|f> if <math|f<rsup|n><around*|(|z<rsub|0>|)>=z<rsub|0>> for some
    <math|n\<in\>\<bbb-N\>>. A fixed-point is a <math|1>-periodic point.
    <cite-detail|bergweiler1993iteration|3.1>
  </definition>

  <\definition>
    A family <math|<around*|(|f<rsub|k>|)>> of holomorphic maps
    <math|U\<rightarrow\><wide|\<bbb-C\>|\<bar\>>> where
    <math|U\<subset\>\<bbb-C\>> is a domain is called a normal family if
    every sequence <math|<around*|(|f<rsub|k>|)>> contains a subsequence that
    converges locally uniformly to a holomorphic limit function
    <math|f:U\<rightarrow\><wide|\<bbb-C\>|\<bar\>>>.
    <cite-detail|milnor2006dynamics|1.3 p30>
  </definition>

  <\definition>
    The (stable) Fatou set <math|F<around*|(|f<around*|(|z|)>|)>> of a
    function <math|f:\<bbb-C\>\<rightarrow\>\<bbb-C\>> is defined by

    <\equation>
      F<around*|(|f<around*|(|z|)>|)>=<around*|{|z\<in\><wide|\<bbb-C\>|^>:<around*|{|f<rsup|\<circ\>n><around*|(|z|)>:n\<in\>\<bbb-N\>|}>
      is defined and constitutes a normal family in some neighborhood of z|}>
    </equation>

    which is the set of points for which <math|lim<rsub|n\<rightarrow\>\<infty\>>f<rsup|\<circ\>n><around*|(|z|)>>
    converges to a fixed-point where <math|f<rsup|\<circ\>n><around*|(|z|)>>
    is composition of <math|f<around*|(|x|)>> with itself <math|n> times,
    e.g. <math|f<rsup|\<circ\>3><around*|(|z|)>=f<around*|(|f<around*|(|f<around*|(|z|)>|)>|)>>.
    A Fatou set <math|F<rsub|f>> of a meromorphic function <math|f> is said
    to be completely invariant, that is, <math|z\<in\>F<rsub|f>> if and only
    if <math|f<around*|(|z|)>\<in\>F<rsub|f>>.
    <cite|baker1991iterates><cite|dominguez1998dynamics>
  </definition>

  <\definition>
    The (chaotic) Julia set <math|J<around*|(|f<around*|(|z|)>|)>> of a
    function <math|f:\<bbb-C\>\<rightarrow\>\<bbb-C\>> is defined as the
    complement of its Fatou set

    <\equation>
      J<around*|(|f<around*|(|z|)>|)>=<wide|\<bbb-C\>|^><mid|\\>F<around*|(|f<around*|(|z|)>|)>
    </equation>

    which is the set of points for which iterated self-maps do not converge.
  </definition>

  <subsubsection|Holomorphic Index>

  <\definition>
    The holomorphic index of a (holomorphic) function
    <math|g:\<Omega\>\<rightarrow\>\<bbb-C\>> is defined by

    <\equation>
      \<iota\><around*|(|g,\<alpha\>|)>=<frac|1|2
      \<pi\>i><big|int><rsub|C><frac|1|1-g<around*|(|z|)>>\<mathd\>z=<around*|{|<frac|1|1-\<lambda\>>:\<lambda\>\<neq\>1|}>
    </equation>

    where <math|C> is a small circle around <math|\<alpha\>> with
    counterclockwise direction. <cite-detail|krh|Proposition 3>
  </definition>

  <subsection|Convergence of Newton's Method, Lipschitz Functions, and The
  Mean Value Theorem>

  <cite|wang2000convergence><cite|ebsd><cite|polyak2006newton><cite|hansen1979global><cite-detail|conte1980elementary|Ch3p.72>
  See <cite-detail|graves1946theory|VIII.2> on the topic of solutions which
  are implicitly defined near an initial solution. <cite|wang2003convergence>

  <\theorem>
    <verbatim|<label|mvt>Theorem of the Mean>. Suppose <math|f> is continuous
    on the finite closed interval <math|<around*|[|a,b|]>> and has a
    derivative either finite or infinite at each point of the open interval
    <math|<around*|(|a,b|)>>. Then there exists a point <math|c> in the open
    interval <math|<around*|(|a,b|)>> such that

    <\equation>
      f<around*|(|b|)>=f<around*|(|a|)>+f<rprime|'><around*|(|c|)><around*|(|b-a|)>
    </equation>

    <cite-detail|graves1946theory|V.I Theorem 4>
  </theorem>

  \;

  Since <math|<wide|N|\<dot\>><rsub|Z><around*|(|y<rsub|n>|)>=0> where
  <math|y<rsub|n>> is <math|n>-th approximation zero would prove that all of
  these roots are simple since by definition the Tayor expansion about the
  root has the form

  <\equation>
    Z<rsub|n><around*|(|t|)>=L<rsub|n><around*|(|t-y<rsub|n>|)><rsup|m<rsub|n>>
  </equation>

  where <math|m<rsub|n>> is the multiplicity of the <math|n>-th root\ 

  <\equation>
    m<rsub|n>=1-<frac|1|<wide|1-<wide|N|\<dot\>>|\<dot\>><around*|(|y<rsub|n>|)>>
  </equation>

  <cite-detail|milnor2006dynamics|Problem 4-g><cite|gill1988compositions>
  <cite|lorentzen1990compositions> <cite-detail|mayer2006immediate|2.3
  Theorem 2.8 p.330>

  \;

  <subsubsection|Lehmer's Phenomena>

  D. H. Lehmer discovered cases where the Riemann zeta function has zeros
  that are ``only just'' on the critical line: two zeros of the zeta function
  are so close together that it is unusually difficult to find a sign change
  between them. This is called ``Lehmer's phenomenon", and first occurs at
  the zeros with imaginary parts 7005.063 and 7005.101, which differ by only
  .04 while the average gap between other zeros near this point is about 1.
  The discovery of non-simple roots, or the discovery of a local positive
  minimum, or a local negative maximum, <nbsp>implies a violation of the
  Riemann hypothesis. <cite-detail|edwardszeta|8.3>\ 

  <\bibliography|bib|tm-alpha|references.bib>
    <\bib-list|27>
      <bibitem*|16><label|bib-krh>T.<nbsp>Kawahira.<newblock> The Riemann
      hypothesis and holomorphic index in complex dynamics.<newblock>
      <with|font-shape|italic|ArXiv e-prints>, feb 2016.<newblock>

      <bibitem*|93><label|bib-bergweiler1993iteration>W.<nbsp>Bergweiler.<newblock>
      Iteration of meromorphic functions.<newblock>
      <with|font-shape|italic|ArXiv Mathematics e-prints>, sep
      1993.<newblock>

      <bibitem*|AGMR14><label|bib-argyros2014convergence>Ioannis<nbsp>Konstantinos
      Argyros, Jos�<nbsp>Manuel Guti�rrez, �ngel<nbsp>Alberto
      Magren�n<localize|, and >Natalia Romero.<newblock> Convergence of the
      relaxed newton's method.<newblock> <with|font-shape|italic|Journal of
      the Korean Mathematical Society>, 51(1):137\U162, 2014.<newblock>

      <bibitem*|BKY><label|bib-baker1991iterates>Irvine<nbsp>N Baker, Janina
      Kotus<localize|, and >Lu Yinian.<newblock> Iterates of meromorphic
      functions: i.<newblock>

      <bibitem*|Bor08><label|bib-borwein2008riemann>Peter Borwein.<newblock>
      <with|font-shape|italic|The Riemann hypothesis: a resource for the
      afficionado and virtuoso alike>, <localize|volume><nbsp>27.<newblock>
      Springer Science & Business Media, 2008.<newblock>

      <bibitem*|Bro04><label|bib-holomorphiczetaflow>Barnett,<nbsp>A.R.<nbsp>Broughan,
      K.A.<newblock> The holomorphic flow of the riemann zeta
      function.<newblock> <with|font-shape|italic|Mathematics of
      Computation>, 73(246):987\U1004, April 2004.<newblock>

      <bibitem*|CB80><label|bib-conte1980elementary>Samuel<nbsp>Daniel
      Conte<localize| and >Carl<nbsp>W<nbsp>De Boor.<newblock>
      <with|font-shape|italic|Elementary numerical analysis: an algorithmic
      approach>.<newblock> McGraw-Hill Higher Education, 1980.<newblock>

      <bibitem*|Dom98><label|bib-dominguez1998dynamics>Patricia
      Dom\Ynguez.<newblock> Dynamics of transcendental meromorphic
      functions.<newblock> <with|font-shape|italic|Ann. Acad. Sci. Fenn.
      Math>, 23(1):225\U250, 1998.<newblock>

      <bibitem*|Edw74><label|bib-edwardszeta>H.M.<nbsp>Edwards.<newblock>
      <with|font-shape|italic|Riemann's Zeta Function>.<newblock> Academic
      Press & Dover, 1974.<newblock>

      <bibitem*|FL14><label|bib-francca2014theory>Guilherme Fran�a<localize|
      and >Andr� LeClair.<newblock> A theory for the zeros of riemann zeta
      and other l-functions.<newblock> <with|font-shape|italic|ArXiv preprint
      arXiv:1407.4358>, 2014.<newblock>

      <bibitem*|FL15><label|bib-z0t>Guilherme Fran�a<localize| and >Andr�
      LeClair.<newblock> Transcendental equations satisfied by the individual
      zeros of riemann \<zeta\>, dirichlet and modular l-functions.<newblock>
      <with|font-shape|italic|Communications in Number Theory and Physics>,
      2015.<newblock>

      <bibitem*|Gil88><label|bib-gill1988compositions>John Gill.<newblock>
      Compositions of analytic functions of the form fn (z)= f n- 1 (fn (z)),
      fn (z)\<rightarrow\> f (z).<newblock> <with|font-shape|italic|Journal
      of computational and applied mathematics>, 23(2):179\U184,
      1988.<newblock>

      <bibitem*|Gra46><label|bib-graves1946theory>L.M.<nbsp>Graves.<newblock>
      <with|font-shape|italic|The theory of functions of real
      variables>.<newblock> International series in pure and applied
      mathematics. McGraw-Hill book company, inc., 1946.<newblock>

      <bibitem*|Han79><label|bib-hansen1979global>Eldon<nbsp>R
      Hansen.<newblock> Global optimization using interval analysis: the
      one-dimensional case.<newblock> <with|font-shape|italic|Journal of
      Optimization Theory and Applications>, 29(3):331\U344, 1979.<newblock>

      <bibitem*|Ivi13><label|bib-HardyZ>A.<nbsp>Ivi�.<newblock>
      <with|font-shape|italic|The Theory of Hardy's Z-Function>.<newblock>
      Cambridge Tracts in Mathematics. Cambridge University Press,
      2013.<newblock>

      <bibitem*|KBM00><label|bib-krcmar2000global>IR Krcmar, MM
      Bozic<localize|, and >DP Mandic.<newblock> Global asymptotic stability
      for rnns with a bipolar activation function.<newblock> <localize|In
      ><with|font-shape|italic|Neural Network Applications in Electrical
      Engineering, 2000. NEUREL 2000. Proceedings of the 5th Seminar on>,
      <localize|pages >33\U36. IEEE, 2000.<newblock>

      <bibitem*|LeC16><label|bib-SO1>Andr� LeClair.<newblock> Riemann
      Hypothesis and Random Walks: the Zeta case.<newblock> 2016.<newblock>

      <bibitem*|Lor90><label|bib-lorentzen1990compositions>Lisa
      Lorentzen.<newblock> Compositions of contractions.<newblock>
      <with|font-shape|italic|Journal of Computational and Applied
      Mathematics>, 32(1-2):169\U178, 1990.<newblock>

      <bibitem*|Map><label|bib-maple>Waterloo,<nbsp>Ontario Maplesoft, a
      division of Waterloo Maple Inc.<newblock> Maple 2016.1.<newblock>

      <bibitem*|MBM09><label|bib-marca2009mean>Davide<nbsp>A Marca, Stefano
      Beltraminelli<localize|, and >Danilo Merlini.<newblock> Mean staircase
      of the riemann zeros: a comment on the lambert w function and an
      algebraic aspect.<newblock> <with|font-shape|italic|ArXiv preprint
      arXiv:0901.3377>, 2009.<newblock>

      <bibitem*|Mil06><label|bib-milnor2006dynamics>John<nbsp>Willard
      Milnor.<newblock> <with|font-shape|italic|Dynamics in one complex
      variable>, <localize|volume> 160.<newblock> Springer, 2006.<newblock>

      <bibitem*|MS06><label|bib-mayer2006immediate>Sebastian Mayer<localize|
      and >Dierk Schleicher.<newblock> Immediate and virtual basins of
      newton's method for entire functions.<newblock> <localize|In
      ><with|font-shape|italic|Annales de l'institut Fourier>,
      <localize|volume><nbsp>56, <localize|pages >325\U336. 2006.<newblock>

      <bibitem*|Pol06><label|bib-polyak2006newton>Boris<nbsp>Teodorovich
      Polyak.<newblock> Newton-kantorovich method and its global
      convergence.<newblock> <with|font-shape|italic|Journal of Mathematical
      Sciences>, 133(4):1513\U1523, 2006.<newblock>

      <bibitem*|Rud06><label|bib-Rudin2006>Walter Rudin.<newblock>
      <with|font-shape|italic|Real & Complex Analysis>.<newblock>
      McGraw-Hill, third<localize| edition>, 2006.<newblock>

      <bibitem*|V.02><label|bib-ebsd>Humberto<nbsp>D.<nbsp>Carri�n
      V.<newblock> Entire functions on banach spaces with a separable
      dual.<newblock> <with|font-shape|italic|Journal of Functional
      Analysis>, 189(2):496\U514, 2002.<newblock>

      <bibitem*|Wan00><label|bib-wang2000convergence>Xinghua Wang.<newblock>
      Convergence of newton's method and uniqueness of the solution of
      equations in banach space.<newblock> <with|font-shape|italic|IMA
      Journal of Numerical Analysis>, 20(1):123\U134, 2000.<newblock>

      <bibitem*|WL03><label|bib-wang2003convergence>Xing<nbsp>Hua
      Wang<localize| and >Chong Li.<newblock> Convergence of newton's method
      and uniqueness of the solution of equations in banach spaces
      ii.<newblock> <with|font-shape|italic|Acta Mathematica Sinica>,
      19(2):405\U412, 2003.<newblock>
    </bib-list>
  </bibliography>
</body>

<\initial>
  <\collection>
    <associate|page-medium|paper>
    <associate|page-type|letter>
  </collection>
</initial>

<\references>
  <\collection>
    <associate|Dt|<tuple|7|2|../attic/stuff/rh.tm>>
    <associate|N|<tuple|18|3|../attic/stuff/rh.tm>>
    <associate|S|<tuple|4|2|../attic/stuff/rh.tm>>
    <associate|Z|<tuple|14|3|../attic/stuff/rh.tm>>
    <associate|ZNewtonFlow|<tuple|24|4|../attic/stuff/rh.tm>>
    <associate|Zz|<tuple|15|3|../attic/stuff/rh.tm>>
    <associate|arz|<tuple|10|2|../attic/stuff/rh.tm>>
    <associate|auto-1|<tuple|1|1|../attic/stuff/rh.tm>>
    <associate|auto-10|<tuple|2.2|6|../attic/stuff/rh.tm>>
    <associate|auto-11|<tuple|3|6|../attic/stuff/rh.tm>>
    <associate|auto-12|<tuple|3.1|6|../attic/stuff/rh.tm>>
    <associate|auto-13|<tuple|3.1.1|7|../attic/stuff/rh.tm>>
    <associate|auto-14|<tuple|3.2|7|../attic/stuff/rh.tm>>
    <associate|auto-15|<tuple|3.2.1|8|../attic/stuff/rh.tm>>
    <associate|auto-16|<tuple|3.2.1|8|../attic/stuff/rh.tm>>
    <associate|auto-2|<tuple|1.1|1|../attic/stuff/rh.tm>>
    <associate|auto-3|<tuple|1.2|3|../attic/stuff/rh.tm>>
    <associate|auto-4|<tuple|1.2.1|4|../attic/stuff/rh.tm>>
    <associate|auto-5|<tuple|1|4|../attic/stuff/rh.tm>>
    <associate|auto-6|<tuple|1.2.2|4|../attic/stuff/rh.tm>>
    <associate|auto-7|<tuple|2|5|../attic/stuff/rh.tm>>
    <associate|auto-8|<tuple|2.1|5|../attic/stuff/rh.tm>>
    <associate|auto-9|<tuple|2|6|../attic/stuff/rh.tm>>
    <associate|bib-HardyZ|<tuple|Ivi13|8|../attic/stuff/rh.tm>>
    <associate|bib-Rudin2006|<tuple|Rud06|8|../attic/stuff/rh.tm>>
    <associate|bib-SO1|<tuple|LeC16|8|../attic/stuff/rh.tm>>
    <associate|bib-argyros2014convergence|<tuple|AGMR14|8|../attic/stuff/rh.tm>>
    <associate|bib-baker1991iterates|<tuple|BKY|8|../attic/stuff/rh.tm>>
    <associate|bib-bergweiler1993iteration|<tuple|93|8|../attic/stuff/rh.tm>>
    <associate|bib-borwein2008riemann|<tuple|Bor08|8|../attic/stuff/rh.tm>>
    <associate|bib-conte1980elementary|<tuple|CB80|8|../attic/stuff/rh.tm>>
    <associate|bib-dominguez1998dynamics|<tuple|Dom98|8|../attic/stuff/rh.tm>>
    <associate|bib-ebsd|<tuple|V.02|8|../attic/stuff/rh.tm>>
    <associate|bib-edwardszeta|<tuple|Edw74|8|../attic/stuff/rh.tm>>
    <associate|bib-francca2014theory|<tuple|FL14|8|../attic/stuff/rh.tm>>
    <associate|bib-gill1988compositions|<tuple|Gil88|8|../attic/stuff/rh.tm>>
    <associate|bib-graves1946theory|<tuple|Gra46|8|../attic/stuff/rh.tm>>
    <associate|bib-hansen1979global|<tuple|Han79|8|../attic/stuff/rh.tm>>
    <associate|bib-holomorphiczetaflow|<tuple|Bro04|8|../attic/stuff/rh.tm>>
    <associate|bib-krcmar2000global|<tuple|KBM00|8|../attic/stuff/rh.tm>>
    <associate|bib-krh|<tuple|16|8|../attic/stuff/rh.tm>>
    <associate|bib-lorentzen1990compositions|<tuple|Lor90|8|../attic/stuff/rh.tm>>
    <associate|bib-maple|<tuple|Map|8|../attic/stuff/rh.tm>>
    <associate|bib-marca2009mean|<tuple|MBM09|8|../attic/stuff/rh.tm>>
    <associate|bib-mayer2006immediate|<tuple|MS06|8|../attic/stuff/rh.tm>>
    <associate|bib-milnor2006dynamics|<tuple|Mil06|8|../attic/stuff/rh.tm>>
    <associate|bib-polyak2006newton|<tuple|Pol06|8|../attic/stuff/rh.tm>>
    <associate|bib-wang2000convergence|<tuple|Wan00|9|../attic/stuff/rh.tm>>
    <associate|bib-wang2003convergence|<tuple|WL03|9|../attic/stuff/rh.tm>>
    <associate|bib-z0t|<tuple|FL15|8|../attic/stuff/rh.tm>>
    <associate|fle|<tuple|22|4|../attic/stuff/rh.tm>>
    <associate|footnote-1|<tuple|1|1|../attic/stuff/rh.tm>>
    <associate|footnr-1|<tuple|1|1|../attic/stuff/rh.tm>>
    <associate|mvt|<tuple|21|7|../attic/stuff/rh.tm>>
    <associate|zzi|<tuple|17|3|../attic/stuff/rh.tm>>
  </collection>
</references>

<\auxiliary>
  <\collection>
    <\associate|bib>
      z0t

      HardyZ

      z0t

      marca2009mean

      SO1

      HardyZ

      borwein2008riemann

      francca2014theory

      HardyZ

      bergweiler1993iteration

      holomorphiczetaflow

      maple

      bergweiler1993iteration

      holomorphiczetaflow

      krcmar2000global

      francca2014theory

      argyros2014convergence

      Rudin2006

      bergweiler1993iteration

      milnor2006dynamics

      baker1991iterates

      dominguez1998dynamics

      krh

      wang2000convergence

      ebsd

      polyak2006newton

      hansen1979global

      conte1980elementary

      graves1946theory

      wang2003convergence

      graves1946theory

      milnor2006dynamics

      gill1988compositions

      lorentzen1990compositions

      mayer2006immediate

      edwardszeta
    </associate>
    <\associate|figure>
      <tuple|normal|<with|mode|<quote|math>|S<around*|(|t|)>> and
      <with|mode|<quote|math>|<around*|{|<around*|(|n-<frac|3|2>|)>\<pi\>-\<vartheta\><around*|(|t|)>:n=1\<ldots\>8|}>>|<pageref|auto-5>>

      <tuple|normal|<with|mode|<quote|math>|<around*|{|M<rsub|Z><around*|(|t|)>:t\<in\>6727\<ldots\>6728|}>>
      near <with|mode|<quote|math>|t=y<rsup|<around*|(|-|)>><rsub|6400>=6727.861353\<ldots\>>|<pageref|auto-9>>
    </associate>
    <\associate|toc>
      <vspace*|1fn><with|font-series|<quote|bold>|math-font-series|<quote|bold>|1<space|2spc>Derivations>
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-1><vspace|0.5fn>

      <with|par-left|<quote|1tab>|1.1<space|2spc>Preliminaries
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-2>>

      <with|par-left|<quote|1tab>|1.2<space|2spc>The Newton Map
      <with|mode|<quote|math>|N<rsub|Z><around*|(|t|)>> of
      <with|mode|<quote|math>|Z<around*|(|t|)>>
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-3>>

      <with|par-left|<quote|2tab>|1.2.1<space|2spc>The LeClair-Fran�a Formula
      and Stirling's Approximation of <with|mode|<quote|math>|ln\<Gamma\>>
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-4>>

      <with|par-left|<quote|2tab>|1.2.2<space|2spc>A Newton-like Map
      <with|mode|<quote|math>|M<rsub|Z><around*|(|t|)>> Which Is Holomorphic
      In The Critical Strip <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-6>>

      <vspace*|1fn><with|font-series|<quote|bold>|math-font-series|<quote|bold>|2<space|2spc>Newton's
      Method and the Hardy Z Function> <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-7><vspace|0.5fn>

      <with|par-left|<quote|1tab>|2.1<space|2spc>Cauchy Sequences and
      Newton's Method <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-8>>

      <with|par-left|<quote|1tab>|2.2<space|2spc>Semi-Local Convergence of
      the Relaxed Newton's Method <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-10>>

      <vspace*|1fn><with|font-series|<quote|bold>|math-font-series|<quote|bold>|3<space|2spc>Appendix>
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-11><vspace|0.5fn>

      <with|par-left|<quote|1tab>|3.1<space|2spc>Complex Dynamics
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-12>>

      <with|par-left|<quote|2tab>|3.1.1<space|2spc>Holomorphic Index
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-13>>

      <with|par-left|<quote|1tab>|3.2<space|2spc>Convergence of Newton's
      Method, Lipschitz Functions, and The Mean Value Theorem
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-14>>

      <with|par-left|<quote|2tab>|3.2.1<space|2spc>Lehmer's Phenomena
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-15>>

      <vspace*|1fn><with|font-series|<quote|bold>|math-font-series|<quote|bold>|Bibliography>
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-16><vspace|0.5fn>
    </associate>
  </collection>
</auxiliary>