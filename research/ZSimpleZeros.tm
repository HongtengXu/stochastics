<TeXmacs|1.99.5>

<style|generic>

<\body>
  <doc-data|<doc-title|The Zeros of the Hardy Z Function are
  Simple>|<doc-author|<author-data|<author-name|Stephen Crowley
  \<less\>stephencrowley214@gmail.com\<gtr\>>|<\author-affiliation>
    <date|>
  </author-affiliation>>>>

  <abstract-data|<\abstract>
    It is proved that the non-trivial roots of the Hardy Z function are
    simple having multiplicity 1 by showing that the fixed-points
    <math|N<rsub|Z><around*|(|\<alpha\>|)>=\<alpha\>> of the Newton map
    <math|N<rsub|Z><around*|(|t|)>=t-<frac|Z<around*|(|t|)>|<wide|Z|\<dot\>><around*|(|t|)>>>
    must have a multiplier <math|\<lambda\><rsub|N<rsub|Z>><around*|(|\<alpha\>|)>=<around*|\||<wide|N<rsub|Z>|\<dot\>><around*|(|\<alpha\>|)>|\|>=<around*|\||<frac|Z<around*|(|\<alpha\>|)><wide|Z|\<ddot\>><around*|(|\<alpha\>|)>|<wide|Z|\<dot\>><around*|(|\<alpha\>|)>>|\|>>=0
    and therefore a multiplicity <math|<tabular|<tformat|<table|<row|<cell|m<rsub|Z><around*|(|\<alpha\>|)>=<frac|1|1-\<lambda\><rsub|N<rsub|Z>><around*|(|\<alpha\>|)>>=<frac|1|1-0>=1.>>>>>>
  </abstract>>

  <section|Preliminary Outline>

  <subsection|Definitions>

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
  <math|ln\<Gamma\><around*|(|z|)>> are determined by continuity from above.\ 

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

  <subsection|Iterated Function Systems>

  <\definition>
    A <verbatim|fixed-point> <math|\<alpha\>> of a function
    <math|f<around*|(|x|)>> is a value <math|\<alpha\>> such that

    <\equation>
      f<around*|(|\<alpha\>|)>=\<alpha\>
    </equation>

    <cite-detail|raadstrom1953iteration|3.>
  </definition>

  <\definition>
    The <verbatim|multiplier> of a fixed point <math|\<alpha\>> of a map
    <math|f<around*|(|x|)>> where <math|f<around*|(|\<alpha\>|)>=\<alpha\>>
    is equal to the derivative of the map evaluated at the point
    <math|\<alpha\>>.\ 

    <\equation>
      \<lambda\><rsub|f><around*|(|\<alpha\>|)>=<wide|f|\<dot\>><around*|(|\<alpha\>|)>
    </equation>

    If <math|<around*|\||\<lambda\><rsub|f><around*|(|\<alpha\>|)>|\|>\<less\>1>
    then <math|\<alpha\>> is a said to be an <strong|attractive fixed-point>
    of the map <math|f<around*|(|x|)>>. If
    <math|<around*|\||\<lambda\><rsub|f><around*|(|\<alpha\>|)>|\|>=1> then
    <math|\<alpha\>> is an <strong|indifferent fixed point>, and if
    <math|<around*|\||\<lambda\><rsub|f><around*|(|\<alpha\>|)>|\|>\<gtr\>1>
    then <math|\<alpha\>> is a <strong|repelling fixed-point>. When
    <math|<around*|\||\<lambda\><rsub|f><around*|(|\<alpha\>|)>|\|>=0> the
    fixed-point <math|\<alpha\>> is said to be
    <verbatim|super-attractive><cite-detail|raadstrom1953iteration|3.>
  </definition>

  <\definition>
    The Newton map of a function <math|g<around*|(|t|)>> is given by

    <\equation>
      N<rsub|g><around*|(|t|)>=t-<frac|g<around*|(|t|)>|<wide|g|\<dot\>><around*|(|t|)>>
    </equation>
  </definition>

  <\lemma>
    (The Product Rule for Derivatives) The derivative
    <math|<wide|f|\<dot\>><around*|(|t|)>> of a function of the form
    <math|f<around*|(|t|)>=g<around*|(|t|)>h<around*|(|t|)>> is given by

    <\equation>
      <frac|\<mathd\>|\<mathd\>t><around*|(|f<around*|(|t|)>g<around*|(|t|)>|)>=<wide|f|\<dot\>><around*|(|t|)>g<around*|(|t|)>+f<around*|(|t|)><wide|g|\<dot\>><around*|(|t|)>
    </equation>
  </lemma>

  <\theorem>
    The multiplier <math|\<lambda\><rsub|N<rsub|f>><around*|(|t|)>> of the
    Newton map <math|N<rsub|f><around*|(|t|)>> of a function
    <math|f<around*|(|t|)>> is given by

    <\equation>
      <tabular*|<tformat|<table|<row|<cell|\<lambda\><rsub|N<rsub|f><around*|(|\<alpha\>|)>>>|<cell|=<frac|f<around*|(|\<alpha\>|)><wide|f|\<ddot\>><around*|(|\<alpha\>|)>|<wide|f|\<dot\>><around*|(|\<alpha\>|)><rsup|2>>>>>>>
    </equation>

    <\proof>
      \;

      \;
    </proof>
  </theorem>

  <\definition>
    The multiplicity <math|m<rsub|N<rsub|f>><around*|(|\<alpha\>|)>> of a
    root <math|f<around*|(|\<alpha\>|)>=0> where
    <math|N<rsub|f><around*|(|\<alpha\>|)>=\<alpha\>> is defined by its
    Taylor expansion about the point <math|\<alpha\>> having the form

    <\equation>
      f<around*|(|*t|)>=c<around*|(|t-\<alpha\>|)><rsup|m<rsub|f><around*|(|\<alpha\>|)>>+<around*|(|higher
      order terms\<ldots\>|)>
    </equation>

    where <math|c\<neq\>0> and <math|m\<geqslant\>1>. The multiplicity of a
    root is related to the multipler <math|\<lambda\><rsub|f><around*|(|\<alpha\>|)>>
    through the formula

    <\equation>
      <tabular|<tformat|<table|<row|<cell|m<rsub|f><around*|(|\<alpha\>|)>>|<cell|=<frac|1|1-\<lambda\><rsub|N<rsub|f>><around*|(|\<alpha\>|)>>>>|<row|<cell|>|<cell|=<frac|1|1-<around*|\||<wide|N<rsub|f>|\<dot\>><around*|(|\<alpha\>|)>|\|>>>>|<row|<cell|>|<cell|=<frac|1|1-<around*|\||<frac|f<around*|(|\<alpha\>|)><wide|f|\<ddot\>><around*|(|\<alpha\>|)>|<wide|f|\<dot\>><around*|(|\<alpha\>|)>>|\|>>>>>>>
    </equation>
  </definition>

  when the fixed-point \ <math|f<around*|(|\<alpha\>|)>=\<alpha\>> is not
  indifferent, that is, when <math|\<lambda\><rsub|N<rsub|f>><around*|(|\<alpha\>|)>\<neq\>1>.
  When <math|\<lambda\><rsub|N<rsub|f>><around*|(|\<alpha\>|)>=1> then
  <math|m<rsub|f><around*|(|\<alpha\>|)>> is not defined thru this formula
  since <math|m<rsub|f><around*|(|t|)>=<frac|1|1-\<lambda\><rsub|N<rsub|f>><around*|(|t|)>>=<frac|1|1-1>=<frac|1|0>>.

  See <cite-detail|milnor2006dynamics|p.52><cite|rhhi>

  <\definition>
    Let

    <\equation*>
      Y<rsub|><around*|(|t|)>=N<rsub|Z><around*|(|t|)>=t-<frac|Z<around*|(|t|)>|<wide|Z|\<dot\>><around*|(|t|)>>
    </equation*>

    be the Newton map of <math|Z<around*|(|t|)>> which has fixed-points
    <math|Y<around*|(|\<alpha\>|)>=\<alpha\>> at the zeros
    <math|Z<around*|(|\<alpha\>|)>=0>.
  </definition>

  <\proposition>
    Y(t) has indifferent fixed-points, <math|<around*|{|\<lambda\><rsub|N<rsub|Z>><around*|(|t|)>=1:t=-<frac|i|2><around*|(|-4n-1|)>\<forall\>n\<in\>\<bbb-Z\><rsup|+<rsub|>>|}>>
    at each trival zero of <math|\<alpha\>>,
    <math|Z<around*|(|-<frac|i|2><around*|(|-4n-1|)>|)>=0>.
  </proposition>

  <\definition>
    A <verbatim|removable singularity> <math|\<beta\>> of a holomorphic
    function <math|f<around*|(|t|)>> is a point
    <math|f<around*|(|\<beta\>|)>> at which the function is undefined yet it
    remains possible to redefine the function in such a way that the
    resulting function is regular in a neighborhood of <math|\<beta\>>.
  </definition>

  <\theorem>
    <math|Y<rsub|><around*|(|t|)>> has super-attractive fixed-points at each
    non-trivial root of <math|Z>, <math|<around*|{|\<alpha\>:Z<around*|(|\<alpha\>|)>=0,Re<around*|(|\<alpha\>|)>\<neq\>0|}>>.
    That is, <math|<around*|{|\<lambda\><rsub|Y><around*|(|\<alpha\>|)>=0:Z<around*|(|\<alpha\>|)>=0,Re<around*|(|\<alpha\>|)>\<neq\>0|}>>.
  </theorem>

  <\proof>
    The numerator of the argument of the absolute value function in the
    multiplier <math|\<lambda\><rsub|Y><around*|(|\<alpha\>|)>=<around*|\||<frac|Z<around*|(|\<alpha\>|)><wide|Z|\<ddot\>><around*|(|\<alpha\>|)>|<wide|Z|\<dot\>><around*|(|\<alpha\>|)>>|\|>>
    is <math|Z<around*|(|\<alpha\>|)><wide|Z|\<ddot\>><around*|(|\<alpha\>|)>>.
    If <math|\<alpha\>> was a geometrically attracting fixed-point
    <math|\<lambda\><rsub|Y><around*|(|\<alpha\>|)>=l> where
    <math|0\<less\>l\<less\>1> instead of superattracting where <math|l=0>
    then its multiplicity would be <math|<frac|1|1-l>=m\<gtr\>1> where
    <math|m> is a positive integer, the multiplier <math|l> would be equal to
    <math|<frac|m-1|m>>. If <math|m\<geqslant\>2> then
    <math|<wide|Z|\<dot\>><around*|(|\<alpha\>|)>=0> which would require
    <math|\<alpha\>> to be a root of <math|<wide|Z|\<ddot\>><around*|(|t|)>>
    removable singularity of either <math|Z<around*|(|t|)>> or
    <math|<wide|Z|\<ddot\>><around*|(|t|)>> so the singularity at
    <math|<frac|1|<wide|Z|\<dot\>><around*|(|t|)>>> as
    <math|t\<rightarrow\>\<alpha\>> would be canceled by a removable
    singularity of <math|Z<around*|(|t|)>> or
    <math|<wide|Z|\<ddot\>><around*|(|t|)>> as
    <math|t\<rightarrow\>\<alpha\>> so that the limit would exist, but since
    both <math|Z<around*|(|t|)>> and <math|<wide|Z|\<ddot\>><around*|(|t|)>>
    are well-defined except at their essential singularities then
    <math|<wide|Z|\<ddot\>><around*|(|\<alpha\>|)>> does not have any
    removable singularities. The only essential singularity of
    <math|Z<around*|(|t|)>> is at <math|-<frac|i|2>> which corresponds to the
    pole of <math|\<zeta\><around*|(|t|)>> at <math|t=1>. The function
    <math|<wide|Z|\<ddot\>><around*|(|t|)>> has essential singularities at
    <math|-<frac|i|2>>, \ <math|<big|cup><rsub|n=1><rsup|\<infty\>>-<frac|i|2><around*|(|4n-3|)>>
    and <math|<big|cup><rsub|n=1><rsup|\<infty\>>+<frac|i|2><around*|(|4n-3|)>>.
  </proof>

  <\theorem>
    The roots of <math|Z<around*|(|t|)>> are simple, that is, have
    multiplicity <math|1>.
  </theorem>

  <\proof>
    Let <math|\<alpha\>\<nin\><big|cup><rsub|n=1><rsup|\<infty\>>-<frac|i|2><around*|(|-4n-1|)>>
    be a non-trivial root where <math|Z<around*|(|\<alpha\>|)>=0> then the
    multiplier of its Newton map is <math|\<lambda\><rsub|N<rsub|Z>><around*|(|\<alpha\>|)>=><math|<around*|\||<frac|Z<around*|(|\<alpha\>|)><wide|Z|\<ddot\>><around*|(|\<alpha\>|)>|<wide|Z|\<dot\>><around*|(|\<alpha\>|)>>|\|>=0
    > therefore its multiplicity is <math|m<rsub|Z><around*|(|t|)>=<frac|1|1-\<lambda\><rsub|N<rsub|Z>><around*|(|t|)>>=<frac|1|1-0>=<frac|1|1>=1>.
    Every simple root of a function <math|f<around*|(|t|)>> is a
    super-attracting fixed-point of <math|N<rsub|f><around*|(|t|)>>. See
    <cite-detail|milnor2006dynamics|p.52>
  </proof>

  <\bibliography|bib|tm-plain|references.bib>
    <\bib-list|4>
      <bibitem*|1><label|bib-HardyZ>A.<nbsp>Ivi¢.<newblock>
      <with|font-shape|italic|The Theory of Hardy's Z-Function>.<newblock>
      Cambridge Tracts in Mathematics. Cambridge University Press,
      2013.<newblock>

      <bibitem*|2><label|bib-rhhi>T.<nbsp>Kawahira.<newblock> The Riemann
      hypothesis and holomorphic index in complex dynamics.<newblock>
      <with|font-shape|italic|ArXiv e-prints>, feb 2016.<newblock>

      <bibitem*|3><label|bib-milnor2006dynamics>John<nbsp>Willard
      Milnor.<newblock> <with|font-shape|italic|Dynamics in one complex
      variable>, <localize|volume> 160.<newblock> Springer, 2006.<newblock>

      <bibitem*|4><label|bib-raadstrom1953iteration>Hans Rådström.<newblock>
      On the iteration of analytic functions.<newblock>
      <with|font-shape|italic|Mathematica Scandinavica>, <localize|pages
      >85\U92, 1953.<newblock>
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
    <associate|RH|<tuple|1|2>>
    <associate|Z|<tuple|3|1>>
    <associate|Zz|<tuple|4|1>>
    <associate|auto-1|<tuple|1|1>>
    <associate|auto-2|<tuple|1.1|1>>
    <associate|auto-3|<tuple|1.2|2>>
    <associate|auto-4|<tuple|12|3>>
    <associate|bib-HardyZ|<tuple|1|3>>
    <associate|bib-milnor2006dynamics|<tuple|3|3>>
    <associate|bib-raadstrom1953iteration|<tuple|4|3>>
    <associate|bib-rhhi|<tuple|2|3>>
    <associate|vartheta|<tuple|2|1>>
  </collection>
</references>

<\auxiliary>
  <\collection>
    <\associate|bib>
      HardyZ

      raadstrom1953iteration

      raadstrom1953iteration

      milnor2006dynamics

      rhhi

      milnor2006dynamics
    </associate>
    <\associate|toc>
      <vspace*|1fn><with|font-series|<quote|bold>|math-font-series|<quote|bold>|1<space|2spc>Preliminary
      Outline> <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-1><vspace|0.5fn>

      <with|par-left|<quote|1tab>|1.1<space|2spc>Definitions
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-2>>

      <with|par-left|<quote|1tab>|1.2<space|2spc>Iterated Function Systems
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-3>>

      <vspace*|1fn><with|font-series|<quote|bold>|math-font-series|<quote|bold>|Bibliography>
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-4><vspace|0.5fn>
    </associate>
  </collection>
</auxiliary>