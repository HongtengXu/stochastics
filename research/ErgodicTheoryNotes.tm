<TeXmacs|1.99.5>

<style|generic>

<\body>
  <doc-data|<doc-title|Notes on Ergodic Theory>|<doc-author|<\author-data|<author-name|Stephen
  Crowley \<less\>stephencrowley214@gmail.com\<gtr\>>>
    \;
  <|author-data>
    \;

    \;
  </author-data>>>

  <section|Dynamical Systems>

  <subsection|Smooth Dynamical Systems on the 2-Dimensional Torus>

  Let

  <\equation>
    Tor<rsup|2>=<frac|\<bbb-R\><rsup|2>|\<bbb-Z\><rsup|2>>
  </equation>

  with cyclic coordinates <math|<around*|(|u,v|)>> and Lebesgue measure
  <math|\<mathd\>u \<mathd\>v> and consider the system of differential
  equations

  <\equation>
    <frac|\<mathd\>u|\<mathd\>t>=A<around*|(|u,v|)>
  </equation>

  <\equation>
    <frac|\<mathd\>v|\<mathd\>t>=B<around*|(|u,v|)>
  </equation>

  where <math|A,B\<in\>C<rsup|r>> where <math|r\<geqslant\>2>. Since this
  system satisifes uniqueness and existence conditions we can let
  <math|<around*|{|T<rsup|t>|}>> be the one-parameter group of translations
  along its solutions. <cite-detail|et|16.1>

  <\theorem>
    If <math|\<lambda\>> is irrational then the flow
    <math|<around*|{|T<rsup|t>|}>> is metrically isomorphic to the special
    flow constructed from the automorphism <math|T<rsub|1>> of rotation of
    the circle <math|S<rsub|1>> by a certain irrational angle
    <math|\<alpha\>> of the form

    <\equation>
      \<alpha\>=<frac|m \<lambda\>+n|p \<lambda\>+q>
    </equation>

    where <math|m,n,p,q\<in\>\<bbb-Z\>> and

    <\equation>
      det<around*|(|<around*|\<\|\|\>|<tabular|<tformat|<table|<row|<cell|m>|<cell|n>>|<row|<cell|p>|<cell|q>>>>>|\<\|\|\>>|)>=\<pm\>1
    </equation>

    and a function <math|F\<in\>C<rsup|5><around*|(|S<rsup|1>|)>>

    <\equation>
      F:S<rsup|1>\<rightarrow\>\<bbb-R\><rsup|1>
    </equation>

    <cite-detail|et|Theorem 16.1>
  </theorem>

  <\bibliography|bib|tm-plain|references>
    <\bib-list|1>
      <bibitem*|1><label|bib-et>I.P.<nbsp>Kornfel\<#2B9\>d,
      S.V.<nbsp>Fomin<localize|, and >J.G.<nbsp>Sinaj.
      <newblock><with|font-shape|italic|Ergodic Theory>.
      <newblock>Grundlehren der mathematischen Wissenschaften : a series of
      comprehensive studies in mathematics. Springer-Verlag, 1982.<newblock>
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
    <associate|auto-1|<tuple|1|?|../../.TeXmacs/texts/scratch/no_name_22.tm>>
    <associate|auto-2|<tuple|1.1|?|../../.TeXmacs/texts/scratch/no_name_22.tm>>
    <associate|auto-3|<tuple|6|?|../../.TeXmacs/texts/scratch/no_name_22.tm>>
    <associate|bib-et|<tuple|1|?|../../.TeXmacs/texts/scratch/no_name_22.tm>>
  </collection>
</references>

<\auxiliary>
  <\collection>
    <\associate|bib>
      et

      et
    </associate>
    <\associate|toc>
      <vspace*|1fn><with|font-series|<quote|bold>|math-font-series|<quote|bold>|1<space|2spc>Dynamical
      Systems> <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-1><vspace|0.5fn>

      <with|par-left|<quote|1tab>|1.1<space|2spc>Smooth Dynamical Systems on
      the 2-Dimensional Torus <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-2>>

      <vspace*|1fn><with|font-series|<quote|bold>|math-font-series|<quote|bold>|Bibliography>
      <datoms|<macro|x|<repeat|<arg|x>|<with|font-series|medium|<with|font-size|1|<space|0.2fn>.<space|0.2fn>>>>>|<htab|5mm>>
      <no-break><pageref|auto-3><vspace|0.5fn>
    </associate>
  </collection>
</auxiliary>