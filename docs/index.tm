<TeXmacs|1.99.6>

<style|generic>

<\body>
  <strong|stochastics> is a little Java library I've put together to do
  estimation, simulation and prediction of a class of 'self-exciting'
  stochastic processes called Hawkes processes.

  For example, you can download <hlink|SPY.mat|https://github.com/crowlogic/stochastics/raw/master/SPY.mat>
  which is the data corresponding to a marked point process of trades of the
  SPDR S&P 500 ETF on a particular 6.5 hour trading day sometime in 2016, the
  autocorrelation of the timing between trades has a long-memory where the
  autocorrletion is significant after a very large number of lags. It is a
  known fact that a long-memory point process like this indicates that the
  process is \Pat criticality\Q in that its branching ratio is fixed
  precisely at 1.0, which is the boundary between extenction and explositoon.
  See and run the program <hlink|ProcessEstimator|https://github.com/crowlogic/stochastics/blob/master/src/stochastic/pointprocesses/selfexciting/ProcessEstimator.java>
  and you will see something like this

  <with|font-base-size|5|<\really-tiny>
    Estimating parameters for SPY.mat

    E[dt]=180.0731913776857<space|2em>\<less\><with|color|red|Average time
    between trades across the entire 6.5 hour session , in units of
    milliseconds >)

    E_0[dt]=101.08133459977739<space|1em><with|color|red|\<less\>Average time
    vetween trades of the first 30 minutes of regularly scheduled trading, in
    units of milliseconds> )

    \[[31;107mspawning \[[95m24\[[32m ExtendedApproximatePowerlawSelfExcitingProcess\[[31;107mes
    to estimate the model parameters \[[94m[,,,b\[[31;107m] most likely to
    have generated the observed sequence of \[[95m17804\[[31;107m
    timestamps\[[m

    \[[31m \ \ \ \ \ \ \ \ \ \ thread-13 \ \ \ \ \ #1/24 \ Storing
    1.0000000000 0.0000000000 3.0255799155 1.7241985792 \ with LL score
    1718889.4428270236\[[39m

    \[[31m \ \ \ \ \ \ \ \ \ \ \ thread-3 \ \ \ \ \ #3/24 \ Storing
    1.0000000000 0.0000000000 3.0251966081 1.7246375499 \ with LL score
    1718889.442804567\[[39m

    \[[31m \ \ \ \ \ \ \ \ \ \ thread-15 \ \ \ \ \ #2/24 \ Storing
    1.0000000000 0.0000000000 3.0255466416 1.7242359036 \ with LL score
    1718889.4428268662\[[39m

    \[[31m \ \ \ \ \ \ \ \ \ \ \ thread-8 \ \ \ \ \ #5/24 \ Storing
    1.0000000000 0.0000000000 3.0295636875 1.7206915112 \ with LL score
    1718889.4412852346\[[39m

    \[[31m \ \ \ \ \ \ \ \ \ \ \ thread-6 \ \ \ \ \ #4/24 \ Storing
    1.0000000000 0.0000000000 3.0255781444 1.7241991514 \ with LL score
    1718889.4428270238\[[39m

    ............

    \[[31m \ \ \ \ \ \ \ \ \ \ \ thread-8 \ \ \ \ #22/24 \ Storing
    1.0000000000 0.0000000000 3.0255731777 1.7242194415 \ with LL score
    1718889.442826963\[[39m

    \[[31m \ \ \ \ \ \ \ \ \ \ \ thread-2 \ \ \ \ #21/24 \ Storing
    1.0000000000 0.0000000000 3.0255960057 1.7241934397 \ with LL score
    1718889.4428270042\[[39m

    estimation completed in 0.542400 minutes at 74.084317 evals/sec

    parameter estimates for ExtendedApproximatePowerlawSelfExcitingProcess{=1.0,=0.0,=3.0295636875196883,b=1.7206915111784111,Z=20.212944519689493,Edt=135.725254656012}

    \ \ \ \ _____________________________________________________________________________________________________________________________________________________________________________________________________________________

    \ \ \ \ \| \ \ \| \ \ \| \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \| b
    \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \| Log-Lik \ \ \ \ \ \ \ \ \ \ \|
    KS(Lambda) \ \ \ \ \ \ \ \ \ \ \ \ \| mean(Lambda) \ \ \ \ \ \ \ \ \ \ \|
    var(Lambda) \ \ \ \ \ \ \ \ \ \ \ \| MM(Lambda)
    \ \ \ \ \ \ \ \ \ \ \ \ \ \ \| LB(Lambda) \ \ \ \ \ \ \ \ \ \ \ \ \|
    MMLB(Lambda) \ \ \ \ \ \ \ \ \ \ \ \| E[dt] \ \ \ \ \ \ \ \ \ \ \ \ \|

    \ \ \ \ \|====================================================================================================================================================================================================================\|

    \ 1. \| 1.0\| 0.03.0295636875196883\| \| 1.7206915111784111\|
    1718889.4412852346\| 0.866404942169227 \| 0.9994319031632158\|
    0.9601678843210508\| 0.02105209014603504 \| 1300.3947948630428\|
    0.15096857806824288\| 135.725254656012 \ \|

    \ 2. \| 1.0\| 0.0\| 3.0255558095751067\| 1.7241933312893514\|
    1718889.4428269528\| 0.8663128815718412\| 0.9994320070964131\|
    0.9597795977901094\| 0.02124602560415001 \| 1299.8196125605334\|
    0.15234993419140458\| 135.67557364607737\|

    \ 3. \| 1.0\| 0.0\| 3.0255794239386953\| 1.7241903564322218\|
    1718889.4428270115\| 0.8663131085461994\| 0.9994320079874562\|
    0.9597784615931496\| 0.021246591921049607\| 1299.8388017200318\|
    0.15235430852694057\| 135.67514849842505\|

    \ 4. \| 1.0\| 0.0\| 3.0255810148945113\| 1.724191059476018 \|
    1718889.442827015 \| 0.8663131076913555\| 0.9994320081242861\|
    0.9597782101066586\| 0.021246717390713044\| 1299.8409012782388\|
    0.15235524253260319\| 135.67508318612306\|

    \ 5. \| 1.0\| 0.0\| 3.025566171530826 \| 1.7242003859853978\|
    1718889.4428270126\| 0.8663128317578 \ \ \| 0.9994320081980411\|
    0.959777480464975 \| 0.021247082064086764\| 1299.8354973653923\|
    0.15235776925594738\| 135.67504778074579\|

    \R\R\R\R\R,

    19. \| 1.0\| 0.0\| 3.0255808158401374\| 1.724205552880895 \|
    1718889.4428270133\| 0.8663128472027217\| 0.9994320093466433\|
    0.9597754181804553\| 0.021248110909794615\| 1299.8536587223991\|
    0.15236544351290007\| 135.67449954231628\|

    20. \| 1.0\| 0.0\| 3.025574541481823 \| 1.7242116484463734\|
    1718889.4428270014\| 0.8663126920826797\| 0.9994320095608489\|
    0.9597746928372614\| 0.0212484731531023 \ \| 1299.853297077291 \|
    0.15236803517130917\| 135.6743971852654 \|

    21. \| 1.0\| 0.0\| 3.025613200916328 \| 1.7241987316644454\|
    1718889.4428269141\| 0.8663132074745514\| 0.9994320103355935\|
    0.9597743908429659\| 0.02124862260120053 \| 1299.8775285846236\|
    0.15236950263288776\| 135.67402776061152\|

    22. \| 1.0\| 0.0\| 3.0255731776959305\| 1.724219441518698 \|
    1718889.442826963 \| 0.8663125427688295\| 0.9994320101572236\|
    0.959773282760179 \| 0.021249176999232344\| 1299.858994166482 \|
    0.1523731753546449 \| 135.67411241608536\|

    23. \| 1.0\| 0.0\| 3.02554664157473 \ \| 1.7242359035798402\|
    1718889.4428268662\| 0.8663120532459874\| 0.9994320102710825\|
    0.9597720193294557\| 0.021249808486941024\| 1299.8491439720772\|
    0.15237754270850248\| 135.6740577131032 \|

    24. \| 1.0\| 0.0\| 3.025196608086467 \| 1.7246375499354134\|
    1718889.442804567 \| 0.8663022985818155\| 0.9994320274511848\|
    0.9597196394329696\| 0.02127596408473742 \| 1299.8839023458731\|
    0.15256566703137792\| 135.66585037341952\|

    \;

    writing timestamp data, compensator, intensity, and innovation to
    stochastics/test0.mat and parameters to stochastics/test0.mat.eapl.model

    \;
  </really-tiny>>

  The output of this process is two files called 'test0.mat' and
  'test0.mat.eapl.model', the first one is a matlab compatible file that has
  the input data, along with the estimated intensity and compensator of the
  process which can be tested for goodness-of-fit and verifying certain
  hypothesis about the data such as the compensator being a unit-rate Poisson
  process with no auto-correlation, that is, if the model is a good fit to
  the data then the mean and variance of the variable '<strong|compensator>'
  (otherwise denoted <math|\<Lambda\>> since matlab doesn't support UTF
  characters in variable names) in test0.mat are both equal to 1 and there
  will be no detectable autocorrelation for any lags other than 0. These
  tests are determined by comparing the statistics of each of the candidate
  solutions

  <math|<block|<tformat|<cwith|1|-1|1|-1|font-base-size|5>|<table|<row|<cell|#>|<cell|\<tau\>>|<cell|\<varepsilon\>>|<cell|\<eta\>>|<cell|b>|<cell|Log-Lik>|<cell|KS<around*|(|\<Lambda\>|)>>|<cell|mean<around*|(|\<Lambda\>|)>>|<cell|var<around*|(|\<Lambda\>|)>>|<cell|M
  M<around*|(|\<Lambda\>|)>>|<cell|LB<around*|(|\<Lambda\>|)>>|<cell|MMLB<around*|(|\<Lambda\>|)>>|<cell|E<around*|[|dt|]>>>|<row|<cell|1>|<cell|1.0>|<cell|0.0>|<cell|3.0255794239386953>|<cell|1.7241903564322218>|<cell|1718889.4428270115>|<cell|0.8663131085461994>|<cell|0.9994320079874562>|<cell|0.9597784615931496>|<cell|0.021246591921049607>|<cell|1299.8388017200318>|<cell|0.15235430852694057>|<cell|135.67514849842505>>|<row|<cell|2>|<cell|1.0>|<cell|0.0>|<cell|3.0255810148945113>|<cell|
  1.724191059476018>|<cell|1718889.442827015>|<cell|
  0.8663131076913555>|<cell| 0.9994320081242861>|<cell|0.9597782101066586>|<cell|0.021246717390713044>|<cell|1299.8409012782388>|<cell|0.15235524253260319>|<cell|135.67508318612306>>|<row|<cell|3>|<cell|1.0>|<cell|0.0>|<cell|3.025566171530826>|<cell|1.7242003859853978>|<cell|1718889.4428270126>|<cell|0.8663128317578>|<cell|0.9994320081980411>|<cell|0.959777480464975>|<cell|0.021247082064086764>|<cell|
  1299.8354973653923>|<cell|0.15235776925594738>|<cell|135.67504778074579>>|<row|<cell|4>|<cell|1.0>|<cell|0.0>|<cell|
  3.025560746696718>|<cell|1.7242055316875902>|<cell|1718889.4428270059>|<cell|0.8663126998659925>|<cell|\|
  0.9994320083726566>|<cell|0.9597768774468013>|<cell|0.02124738322404185>|<cell|1299.8350734465043>|<cell|0.15235992187817635>|<cell|135.67496433748133>>|<row|<cell|\<ldots\>>|<cell|\<ldots\>.>|<cell|\<ldots\>>|<cell|\<ldots\>>|<cell|\<ldots\>>|<cell|\<ldots\>>|<cell|\<ldots\>>|<cell|\<ldots\>>|<cell|\<ldots\>>|<cell|\<ldots\>>|<cell|\<ldots\>>|<cell|\<ldots\>>|<cell|\<ldots\>>>|<row|<cell|24>|<cell|1.0>|<cell|0.10164\<ldots\>>|<cell|0.1>|<cell|0.0>|<cell|1718423.379808068>|<cell|0.8685636719501787>|<cell|0.9995379499758154>|<cell|1.0588055428354197>|<cell|0.029402878162822277>|<cell|4026.1017131795033>|<cell|0.24406747627945283>|<cell|239.45818456723876>>>>>>

  \;

  The column-labels indicate the parameters and summary statistics of a set
  of candidate solutions which are local minimima of the likelihood surface
  of the data given the parameters <math|\<tau\>,\<varepsilon\>,\<eta\>,b>
  and they are ranked in order of best fit to worst fit according to the
  column <math|MMLB<around*|(|\<Lambda\>|)>>. The summary statistics are

  <\itemize-dot>
    <item>Log-Lik=<math|<with|mode|text|>ln\<cal-L\><around*|(|N<around*|(|t|)><rsub|t\<in\><around*|[|0,T|]>>|)>>
    is the logarithm of the likelihood score which is defined in closed-form
    by

    <\equation*>
      <tabular|<tformat|<table|<row|<cell|<with|mode|text|>ln\<cal-L\><around*|(|N<around*|(|t|)><rsub|t\<in\><around*|[|0,T|]>>|)>>|<cell|=<big|int><rsub|0><rsup|T><around*|(|1-\<lambda\><around*|(|s|)>|)>\<mathd\>s+<big|int><rsub|0><rsup|T>ln\<lambda\><around*|(|s|)>\<mathd\>N<rsub|s>>>|<row|<cell|>|<cell|=T-<big|int><rsub|0><rsup|T>\<lambda\><around*|(|s|)>\<mathd\>s+<big|int><rsub|0><rsup|T>ln\<lambda\><around*|(|s|)>\<mathd\>N<rsub|s>>>>>>
    </equation*>

    <item><math|KS<around*|(|\<Lambda\>|)>> is actually 1 minus the
    Kolomogorov-Smirnov statistic\ 

    <\equation*>
      D<rsub|n>=sup<rsub|x><around*|\||F<rsub|n><around*|(|x|)>-F<around*|(|x|)>|\|>
    </equation*>

    <item>mean(\<#39B\>)=<math|<frac|1|n><big|sum><rsub|i=1><rsup|n>\<Lambda\><rsub|i>>

    <item><math|var<around*|(|\<Lambda\>|)>=<frac|1|n><big|sum><rsub|i=1><rsup|n><around*|(|\<Lambda\><rsub|i>-mean<around*|(|\<Lambda\>|)>|)><rsup|2>>

    <item><math|MM<around*|(|\<Lambda\>|)>> is a moment-matching measure
    defined by

    <\equation*>
      M M<around*|(|\<Lambda\>|)>=<around*|\||mean<around*|(|\<#39B\>|)>-1|\|>+<around*|\||var<around*|(|\<Lambda\>|)>-1|\|>
    </equation*>

    <item><math|LB<around*|(|\<Lambda\>|)>> is the Ljung-Box statistic, which
    tests for serial autocorrelation, defined by

    <\equation*>
      Q=n<around*|(|n+2|)><big|sum><rsub|k=1><rsup|h><frac|<wide|\<rho\>|^><rsup|2><rsub|\<kappa\>>|n-k>
    </equation*>

    where <math|<wide|\<rho\>|^><rsub|k>> is the sample autocorrelation at
    lag <math|k> given by\ 

    <\equation*>
      <wide|\<rho\>|^><rsub|k>=<frac|1|<around*|(|n-k|)>><big|sum><rsub|i=1><rsup|n-k>\<Lambda\><rsub|i>
      \<Lambda\><rsub|i+k>
    </equation*>

    <item>MMLB(<math|\<Lambda\>>) is a combination moment-matching
    autocorrelation minimizing metric

    <\equation*>
      MMLB<around*|(|\<Lambda\>|)>=M M<around*|(|\<Lambda\>|)>*log<around*|(|1+LB<around*|(|\<Lambda\>|)>|)>
    </equation*>

    <item>and finally <math|E<around*|[|dt|]>> is the unconditional expected
    mean time between points of the process

    <\equation*>
      <tabular|<tformat|<table|<row|<cell|E<around*|[|dt|]>>|<cell|=<big|int><rsub|0><rsup|\<infty\>>t
      f<around*|(|t|)>\<mathd\>t>>|<row|<cell|>|<cell|=<big|int><rsub|0><rsup|\<infty\>>
      <frac|t|Z><big|sum><rsub|j=1><rsup|P>\<alpha\><rsub|j>
      e<rsup|-\<beta\><rsub|j> t>\<mathd\>t>>|<row|<cell|>|<cell|=<frac|<big|sum><rsub|j=1><rsup|P>\<gamma\><around*|(|j,2|)>|<around*|(|<big|prod><rsub|j=1><rsup|P>\<beta\><rsub|j>|)><around*|(|<big|sum><rsub|j=1><rsup|P>\<gamma\><around*|(|j,1|)>|)>Z>>>>>>
    </equation*>

    where

    <\equation>
      \<gamma\><rsup|n><rsup|><around*|(|k|)>=<big|prod><rsub|j=1><rsup|P><choice|<tformat|<table|<row|<cell|\<alpha\><rsub|j>>|<cell|j=k>>|<row|<cell|\<beta\><rsup|n><rsub|j>>|<cell|j\<neq\>k>>>>><label|gamma>
    </equation>

    and

    <\equation>
      \<gamma\><around*|(|k|)>=\<gamma\><rsup|1><around*|(|k|)>
    </equation>

    when <math|n> is not denoted and the exponential powerlaw weights
    <math|\<alpha\><rsub|j>> and <math|\<beta\><rsub|j>> are a function of
    the parameters <math|\<tau\>,\<varepsilon\>,\<eta\>,b> defined by

    <\equation*>
      \;
    </equation*>

    <\equation>
      \<alpha\><rsub|j>=<choice|<tformat|<table|<row|<cell|*<around*|(|\<tau\>
      m<rsup|i>|)><rsup|-<around*|(|1+\<varepsilon\>|)>>>|<cell|j\<less\>M>>|<row|<cell|b>|<cell|j=P>>>>>
    </equation>

    <\equation*>
      \<beta\><rsub|j>=<choice|<tformat|<table|<row|<cell|\<tau\>m<rsup|i><rsub|>>|<cell|j\<less\>P>>|<row|<cell|\<eta\><rsup|-1>>|<cell|j=P>>>>>
    </equation*>

    and

    <\equation*>
      Z=<big|sum><rsub|j=1><rsup|P><frac|\<alpha\><rsub|j>|\<beta\><rsub|j>>
    </equation*>

    with <math|P=M+1> and <math|M=15> and
    <math|m=e<rsup|<frac|ln<around*|(|60000|)>|M>>>so that the exponential
    powerlaw-approximation covers about 15 minutes with a resolution of 1
    minute. The additional weight <math|b> to allow for a smooth-drop to zero
    as described in <hlink|Critical reflexivity in financial markets: a
    Hawkes process analysys|https://arxiv.org/abs/1302.1405>
  </itemize-dot>

  \;

  All times are in units of milliseconds unless otherwise specified. As we
  can see, the theoretical mean of the estimated model is 135.67..ms which is
  not far from the emperical sample mean of
  <math|E<rsub|0>>[dt]=101.08133459977739 which is the sample mean of the
  mean time between trades in the first 30 minutes. The mean and variance of
  the compensator is also very close to 1, and the autocorrelation is
  relatively small compared to the input data.

  Here is a qqplot, or quantile-quantile plot, of the compensated process
  <math|\<#39B\><rsub|i>> versus the unit exponential distribution,

  <big-figure|<image|qq.png|429pt|371pt||>|QQ plot of <math|\<Lambda\>>
  versus the unit exponential distribution <math|e<rsup|-t>>>

  \;
</body>

<\initial>
  <\collection>
    <associate|page-type|letter>
  </collection>
</initial>

<\references>
  <\collection>
    <associate|auto-1|<tuple|1|?|../../../.TeXmacs/texts/scratch/no_name_23.tm>>
    <associate|gamma|<tuple|?|2|../../../.TeXmacs/texts/scratch/no_name_23.tm>>
  </collection>
</references>