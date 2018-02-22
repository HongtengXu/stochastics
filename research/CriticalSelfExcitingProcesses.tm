<TeXmacs|1.99.5>

<style|generic>

<\body>
  <doc-data|<doc-title|Critical Self-Exciting Processes and Their Relation To
  Parabolic Fixed-Points of Holomorphic Dynamical
  Systems>|<doc-author|<author-data|<author-name|Stephen
  Crowley>|<\author-affiliation>
    stephencrowley214@gmail.com
  </author-affiliation>>>>

  A self-exciting process is a (simple) point process whose conditional
  intensity function is a function of the times between previous occurances
  of the process

  <\equation>
    <tabular*|<tformat|<table|<row|<cell|\<lambda\><around*|(|t|)>>|<cell|=\<lambda\><rsub|0><around*|(|t|)>+<big|int><rsub|0><rsup|t>\<nu\><around*|(|t-s|)>\<mathd\>N<rsub|s>>>|<row|<cell|>|<cell|=\<lambda\><rsub|0><around*|(|t|)>+<big|sum><rsub|j=1><rsup|N<rsub|t>>\<nu\><around*|(|t-T<rsub|j>|)>>>>>>
  </equation>

  where <math|N<rsub|t>> is the counting function which counts the number of
  occurances of the process up to time <math|T>

  <\equation>
    N<rsub|t>=<big|sum><rsub|T<rsub|i>\<less\>t>\<theta\><around*|(|t-T<rsub|i>|)>
  </equation>

  where

  <\equation>
    \<theta\><around*|(|t|)>=<choice|<tformat|<table|<row|<cell|0>|<cell|t\<leqslant\>0>>|<row|<cell|1>|<cell|t\<gtr\>0>>>>>
  </equation>

  is the Heaviside step function and <math|<around*|{|T<rsub|i>:T<rsub|i>\<less\>T<rsub|i+1>|}>\<in\>\<bbb-R\>>
  is the time of occurance of the <math|i>-th event of a process. The
  variable <math|\<lambda\><rsub|0><around*|(|t|)>> is the deterministic
  baseline(aka background) intensity of the process which has the mean value

  <\equation>
    <tabular|<tformat|<table|<row|<cell|<wide|\<lambda\>|\<bar\>><rsub|0>>|<cell|=lim<rsub|t\<rightarrow\>\<infty\>><frac|1|t><big|int><rsub|0><rsup|t>\<lambda\><rsub|0><around*|(|t|)>\<mathd\>t>>|<row|<cell|>|<cell|=lim<rsub|t\<rightarrow\>\<infty\>><big|int><rsub|0><rsup|t><frac|1|t>\<lambda\><rsub|0><around*|(|t|)>\<mathd\>t>>|<row|<cell|>|<cell|=<big|int><rsub|0><rsup|\<infty\>><frac|1|t>\<lambda\><rsub|0><around*|(|t|)>\<mathd\>t>>|<row|<cell|>|<cell|=<big|int><rsub|0><rsup|\<infty\>><frac|\<lambda\><rsub|0><around*|(|t|)>|t>\<mathd\>t>>>>>
  </equation>

  where the bar indicates that it is the mean of the function
  <math|\<lambda\><rsub|0><around*|(|t|)>> over its domain
  <math|\<bbb-R\><rsup|+>>. When <math|\<lambda\><rsub|0><around*|(|t|)>=\<lambda\><rsub|0>>
  is a constant function we have <math|<wide|\<lambda\>|\<bar\>><rsub|0>=\<lambda\><rsub|0>>.
  The function <math|\<nu\><around*|(|t|)>\<gtr\>0> is a real-valued function
  called the kernel function, as it determines how the conditional intensity
  of the process evolves as a function of the occurance times of the process.
  The branching ratio of the process <math|\<rho\>> is what determines its
  long-time behaviour and it is equal to the Lebesgue measure of
  <math|\<nu\><around*|(|t|)>>

  <\equation>
    \<rho\>=<big|int><rsub|0><rsup|\<infty\>>\<nu\><around*|(|t|)>\<mathd\>t
  </equation>

  The unconditional intensity <math|\<lambda\>> of the process <math|N> is
  then given by

  <\equation>
    <tabular|<tformat|<table|<row|<cell|\<lambda\>=E<around*|[|\<lambda\><around*|(|t|)>|]>>|<cell|=<frac|<big|int><rsub|0><rsup|\<infty\>><frac|\<lambda\><rsub|0><around*|(|t|)>|t>\<mathd\>t|1-<big|int><rsub|0><rsup|\<infty\>>\<nu\><around*|(|t|)>\<mathd\>t>>>|<row|<cell|>|<cell|=<frac|<wide|\<lambda\>|\<bar\>><rsub|0>|1-\<rho\>>>>|<row|<cell|>|<cell|>>>>>
  </equation>

  when <math|\<rho\>\<less\>1>. When <math|\<rho\>=1> the unconditional
  intensity is taken as a limit

  <\equation>
    \<lambda\>=lim<rsub|s\<rightarrow\>\<infty\>><frac|<big|int><rsub|0><rsup|s><frac|\<lambda\><rsub|0><around*|(|t|)>|t>\<mathd\>t|1-<big|int><rsub|0><rsup|s>\<nu\><around*|(|t|)>\<mathd\>t>
  </equation>

  TODO: the numerator in <cite|chp> is different. The structure of these
  equations is related to that in <cite-detail|milnor2006dynamics|Lemma 12.9>

  In <cite-detail|milnor2006dynamics|Lemma 12.9> there is an 'index
  computation problem' described.\ 

  Consider a fixed point of multiplicity <math|n+1\<geqslant\>2> which is in
  the normal form

  <\equation>
    f<around*|(|z|)>=z+a z<rsup|n+1>+b z<rsup|2n+1>+<around*|(|higher
    terms|)>
  </equation>

  It can be shown that the index <math|l<around*|(|f,0|)>> is equal to the
  ratio <math|<frac|b|a<rsup|2>>>

  <\equation>
    l<around*|(|f,0|)>=<frac|b|a<rsup|2>>
  </equation>

  where

  <\equation>
    l<around*|(|f,<wide|z|^>|)>=<frac|1|2 \<pi\>
    i><big|oint><frac|\<mathd\>z|z-f<around*|(|z|)>>
  </equation>

  is the <verbatim|residue fixed point index> of <math|f> at
  <math|<wide|z|^>> where the integral is taken as a small loop in the
  positive direction around <wide|<math|z>|^>.

  <\bibliography|bib|tm-plain|references>
    <\bib-list|2>
      <bibitem*|1><label|bib-chp>Pierre Brémaud<localize| and >Laurent
      Massoulié. <newblock>Hawkes branching point processes without
      ancestors. <newblock><with|font-shape|italic|J. Appl. Probab.>,
      38(1):122\U135, 03 2001.<newblock>

      <bibitem*|2><label|bib-milnor2006dynamics>John Milnor.
      <newblock><with|font-shape|italic|Dynamics in One Complex Variable>.
      <newblock>Annals of Mathematics Studies 160. Princeton University
      Press, 2nd<localize| edition>, 2006.<newblock>
    </bib-list>
  </bibliography>

  \ 

  \;

  \;
</body>

<\initial>
  <\collection>
    <associate|page-type|letter>
  </collection>
</initial>

<\references>
  <\collection>
    <associate|auto-1|<tuple|10|?|../../../.TeXmacs/texts/scratch/no_name_19.tm>>
    <associate|bib-chp|<tuple|1|?|../../../.TeXmacs/texts/scratch/no_name_19.tm>>
    <associate|bib-milnor2006dynamics|<tuple|2|?|../../../.TeXmacs/texts/scratch/no_name_19.tm>>
  </collection>
</references>

<\auxiliary>
  <\collection>
    <\associate|bib>
      chp

      milnor2006dynamics
    </associate>
    <\associate|toc>
      <vspace*|1fn><with|font-series|<quote|bold>|math-font-series|<quote|bold>|Bibliography>
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-1><vspace|0.5fn>
    </associate>
  </collection>
</auxiliary>