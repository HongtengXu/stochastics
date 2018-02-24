# Copyright 2009-2017 by Stephen Crowley
#
#    cl.mpl is free software: you can redistribute it and/or modify
#    it under the terms of the GNU General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version, the full text of which is
#    contained in copyright.txt in the same directory containing this
#    file.
#
#    This program is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU General Public License for more details.
#
#    You should have received a copy of the GNU General Public License
#    along with this program.  If not, see <http://www.gnu.org/licenses/>.
cl := module ()

export procopts, ri, ria, sucratio, sucdiff, sexpand, oddchr, hypersum, simp, nd, ndl, nds, ndlseq, ndseq, nde,ss, srf, swap, abslog, PisotV, tosf, cseq,
    lrlim, lim, llim, rlim, splitint, lsum, rial, ril, solverec, fseq, pseq, tolist,  radroot, rowlist, collist, diaglist, recfactorsol, colrecfactorsol,
    diagrecfactorsol, rowrecfactorsol, tospecial, topl, aesort,  desort, timevalf, flist, recsol, colrecsol, diagrecsol, rowrecsol,  mdiv, zhpcont, zhp,
    hzdic,  HF, numrows, numcols, coeflist, fa, fiz, eps, U, pgl, res, eye, isonepf, to1pf, indsyms, inf, upi, tof, desc, clist, topoly, intsep, intsplit,
    nthd, limdlist, evaldlist, dlist, upis, upinert, upinerts, tosumf, disconlim, goldenratio, numers, denoms, head, tail, ltrim, rtrim, iterate, hi, pwh,
    one, hstep, gsf, himp, timeval, hilen, ind, maxstep, minstep, grid, translist, riaf, evalgf, tmatrix, uniq, div0handler, invophandler, inithandlers,
    loadtable, savetable, tostr, remtable, saveremtable,merge,mergeremtable,listsum,longeval,reslist,signchanges,rialistplot,writegif,spd,Madelung,w,remind,
    pif,croots,writeimg,writeimages,rgb,zetaf,hnpw,hn,hm,hmzn,hmzeta,hmzoffset,zerof,hsym,imsort,relist, imlist,abslist,findsublist,resort,crootsn,crootlist,
    pw,sucmax,topw,wmrad,torat,iimt,iilt,zwm,zwmlt,wl,cplot,plotparams,pp, wnc,hnc,minmax,wmarq,wm1r,pc,fadisp,wb,lnl,toset,ua,remtablesize,tset,expandPow,vartheta,S,Z;

option package;

uses LinearAlgebra,gfun,PolynomialTools,plots,StringTools,RootFinding,vartheta;

    vartheta := proc (t) options operator, arrow; -((1/2)*I)*(lnGAMMA(1/4+((1/2)*I)*t)-lnGAMMA(1/4-((1/2)*I)*t))-(1/2)*ln(Pi)*t end proc:

    S := to1pf(argument(Zeta(1/2+I*t))/Pi):

    Z := to1pf(exp(I*vartheta(t))*Zeta(1/2+I*t)):


expandPow := proc (l)
local newl, i, x, var, mult;
options operator, arrrow;
    newl := [];
    for i to nops(l) do
        x := l[i];
        if nops(x) = 1 then
            newl := [op(newl), l[i]]
        else
            var := op(1, x);
            mult := op(2, x);
            newl := [op(newl), op(flist(proc (k) options operator, arrow; var end proc, 1 .. mult))]
        end if end do;
    return newl;
end proc:

tset := proc (T::name, t::list) options operator, arrow; toset(flist(proc (n) options operator, arrow; T[n] = t[n] end proc, 1 .. nops(t))) end proc:


remtablesize := proc(f) nops(op(2, remtable(f))); end proc;

ua := proc() description "unapply"; unapply(_rest); end proc;

numrows := x->LinearAlgebra[RowDimension](x);

numcols := x->LinearAlgebra[ColumnDimension](x);

toset := proc (x)
      description "convert(x,set)";
      return convert(x, set);
end proc:
pc := proc (x)
     options operator, arrow;
     description "[|x|,arg(x)]";
     return tolist(polar(x));
end proc:

minmax := proc (l) options operator, arrow; return min(l) .. max(l); end proc:

procopts := {operator, remember, :-arrow};

div0handler := proc (operator, operands, default_value) NumericStatus(division_by_zero = false); return infinity end proc;

invophandler := proc (operator, operands, def) if operands = [0, infinity] then return 0 else return def end if end proc;

inithandlers := proc ()
     NumericEventHandler(division_by_zero = div0handler);
     NumericEventHandler(invalid_operation = invophandler);
     interface(rtablesize = 20);
end proc;


tostr := proc(f) convert(f,string); end proc;

indsyms := proc (f) description "returns independent symbols of an expression"; return `minus`(indets(f, symbol), indets(f, constant)) end proc;

isonepf := proc (f) options operator, arrow; type(f, procedure(anything)) or type(f, procedure(algebraic)) end proc;

to1pf := proc (f) local id;
options operator, arrow;
description "converts expression to a 1-parameter function if it isn't already, can only have 1 indeterminant if not";
if isonepf(f) then return f end if;
if type(f, procedure) then error f, "cannot be a function of more than 1 parameter" end if;
if type(f, symbol) then error f, "cannot be a symbol" end if;
id := indsyms(f);
if 1 < nops(id) then error f, "has more than 1 indeterminant" else return unapply(f, op(id)) end if;
end proc;

inf := infinity;

ri := unapply([Re(s), Im(s)], s, proc_options = procopts);

ria := proc(s) options operator,arrow; return [Re(s), Im(s), abs(s)]; end proc;

sucratio := proc (l) options operator, arrow; [seq(l[i+1]/l[i], i = 1 .. nops(l)-1)] end proc;

sucdiff := proc (l) options operator, arrow; [seq(l[i+1]-l[i], i = 1 .. nops(l)-1)] end proc;

sexpand := proc (f, z, n)  return map(proc (f) options operator, arrow; eval(f, op(1, z) = 1) end proc, convert(sort(convert(series(f, z, n), polynom), op(1, z), ascending), list)) end proc;

oddchr := proc (s) options operator, arrow; 1/2-(1/2)*exp(I*Pi*s) end proc;

 hypersum := proc (f) option remember; return simplify(value(convert(simplify(f), Sum))) end proc;

 simp := proc (x)  return simplify(x) end proc;

 nd := proc (f, n)  description "n-th derivative"; if n = 0 then return f else return diff((to1pf(f))(x), `$`(x, n)) end if end proc;

 ndseq := proc (f, x, n)  [seq(nd(f, x, m), m = n)] end proc;

 nds := proc (f, x, n) if n = 0 then return simplify(f) else return simplify(nd(f, x, n)) end if end proc;

 nde := proc (f, x, n, ev)  return eval(nd(f, x, n), ev) end proc;

 ndl := proc (f, x, n, l)  description "limit of n-th simplified derivative"; return limit(nds(f, x, n), l) end proc;

 ndlseq := proc (f, x, n, l) [seq(ndl(f, x, m, l), m = n)] end proc;

 ss := proc (f, r)  return [seq(simplify((to1pf(f))(x)), x = r)] end proc;

srf := proc (n) options remember; local x; if n <= 1 then return [1, 1] elif n < 4 then return [1, factorial(n)] else x := tolist(sqrt(factorial(n))); x[2] := x[2]^2; return x end if end proc;

 swap := proc (f, a, b) options operator, arrow; return subs({a = b, b = a}, f) end proc;

 tosumf := proc (f) options  operator, arrow; description "convert(convert(f,Sum),hypergeom)"; return convert(convert(f, Sum), hypergeom) end proc;

 tof := proc (f) options  operator, arrow; description "convert(f,hypergeom)"; return convert(f, hypergeom) end proc;

 tosf := proc (f) options  operator, arrow; return convert(f, StandardFunctions) end proc;

 tospecial := proc (f) options  operator, arrow; return convert(f, to_special_function) end proc;

 topl := proc (f) options  operator, arrow; return convert(f, polylog) end proc;

 cseq := proc (x, n) options operator, arrow; [seq(x, j = 1 .. n)] end proc;

 lrlim := proc (f, l) options operator, arrow; [limit((to1pf(f))(x_), x_ = l, left), limit((to1pf(f))(x_), x_ = l, right)] end proc;

 lim := proc (f, p) options operator, arrow; limit((to1pf(f))(x), x = p) end proc;

 llim := proc (f, p) options operator, arrow; limit((to1pf(f))(x), x = p, left) end proc;

 rlim := proc (f, p) options operator, arrow; limit((to1pf(f))(x), x = p, right) end proc;

 lsum := proc (l) options operator, arrow; add(l[i], i = 1 .. nops(l)) end proc;

rial := proc (l) options operator, arrow; map(proc (x) options operator, arrow; op(ria(x)) end proc, l) end proc;

ril := proc (l) options operator, arrow; map(proc (x) options operator, arrow; op(ri(x)) end proc, l) end proc;

fseq := proc (l::list, j:=1) options arrow; description "factorial sequence [l[n]=l[n]/((j+n)!)"; [seq(l[n]/factorial(j+n), n = 1 .. nops(l))] end proc;

tolist := proc (l) options operator, arrow; convert(l, list) end proc;

recsol := proc (l::list, m::symbol := 'n', offset := 0, returneq::boolean := false)
local r, n, tmp, rs;
description "wrapper around solverec/rsolve, returns (n-offset)->q(n) where q(n) is solution to recurrence and offset defaults to 0, returneq specifies where a list of [rec,sol] or just sol is returned, defaults to false, if rec is not solved then it is returned";
rs := op(1, listtorec(l, u(n_)));
r := eval(rs, n_ = m);
try
tmp := eval(rsolve(r, u(n_), _rest), n_ = m-offset)
catch:
WARNING(sprintf("%s: %s", lastexception, lasterror));
return r;
end try;
if returneq then return [r, unapply(tmp, m)] else return unapply(tmp, m) end if;
end proc;

rowlist := proc (m::Matrix, k) tolist(Row(m, k)) end proc;

collist := proc (m::Matrix, k) tolist(Column(m, k)) end proc;

diaglist := proc (m::Matrix, k) tolist(Diagonal(m, -k)) end proc;

colrecsol := proc (m::Matrix, k) recsol(ltrim(collist(m, k), k-1), _rest); end proc:

rowrecsol := proc (m::Matrix, k) recsol(head(rowlist(m, k), k), _rest); end proc;

diagrecsol := proc (m::Matrix, k) recsol(diaglist(m, k), _rest); end proc;

aesort := proc (f, s) options operator, arrow; return sort(expand(f), s, ascending) end proc;

desort := proc (f, s) options operator, arrow; return sort(expand(f), s, descending) end proc;

timevalf := proc (expr::uneval,minprinttime::numeric:=1)
 local st, retval,el;
 option arrow;
 st := time[real]();
 retval := evalf(expr, _rest);
 el:=time[real]()-st;
 if el >= minprinttime then printf("%.2fs elapsed\n", el) end if;
 return retval;
end proc;

timeval := proc (expr::uneval,fname:="",minprinttime::numeric:=1)
 local st, retval,el;
 option arrow;
 st := time[real]();
 retval := eval(expr);
 el:=time[real]()-st;
 if el>=minprinttime then
   if not fname="" then
       try
        appendto(fname);
        printf("%a elapsed, expr=%a\n",el,expr);
       finally
        writeto(terminal);
       end try;
   end if;
   printf("%a elapsed\n", el);
 end if;
 return retval;
end proc;

flist := proc (f, rng::range, step::numeric:=1) options arrow; [seq((to1pf(f))(x_), x_ = rng, step )]; end proc;

coeflist := proc (p, t) options operator, arrow; CoefficientList(p, t) end proc;

fa := proc()
   options arrow;
   description "FunctionAdvisor(...)";
   return FunctionAdvisor(_rest);
end proc:

fadisp := proc()
   options arrow;
   description "FunctionAdvisor(display,...)";
   return FunctionAdvisor(display, _rest);
end proc:

fiz := proc (f) options operator, arrow; eval(f, zeta = Zeta) end proc;

eps := epsilon;

pgl := proc (f, r) options operator, arrow; plot((to1pf(f))(x), x = r, gridlines, axes=boxed,smartview=false, _rest) end proc;

iterate := proc (f, x, n) local l, y, i; y := x; l := [y]; for i to n do y := f(y); l := [op(l), y] end do; return l end proc;

res := proc (f, x) options operator, arrow; residue((to1pf(f))(x_), x_ = x) end proc;

eye := proc (x) options operator, arrow; x end proc;

desc := proc (f) options operator, arrow; Describe(f) end proc;

clist := proc (l) options operator, arrow; PolynomialTools[CoefficientList]((to1pf(l))(x), x) end proc;

topoly := proc (f) options operator, arrow; convert(f, polynom) end proc;

intsep := proc (f, rng) options operator, arrow; map(proc (f) options operator, arrow; int(f, t = rng) end proc, tolist((to1pf(f))(t))) end proc;

intsplit := proc (f, rng) options operator, arrow; lsum(intsep(f, rng)) end proc;

nthd := proc (f, n) description "n-th derivative"; if n = 0 then return (to1pf(f))(x) else return diff((to1pf(f))(x), `$`(x, n)) end if end proc;

limdlist := proc (f, rng, x)
 description "evaluated at the limit derivative list";
 return map(proc (g) options operator, arrow; limit((to1pf(g))(y), y = x) end proc, dlist(f, rng))
end proc;

evaldlist := proc (f, rng, x) description "evaluated derivative list"; return map(proc (g) options operator, arrow; (to1pf(g))(x) end proc, dlist(f, rng)) end proc;

dlist := proc (f, rng) options remember, operator, arrow; description "derivative list"; return flist(proc (n) options operator, arrow; nthd(f, n) end proc, rng) end proc;

upis := proc (f, y) local g; options remember, operator, arrow; g := upi(f, n); return [g, sum(g*y^n, n = 1 .. infinity)] end proc;

upinerts := proc (f, y) local g; options remember, operator, arrow; g := upinert(f, n); return [g, sum(g*y^n, n = 1 .. infinity)] end proc;

disconlim := proc (f)
 local dis, g, lr;
 options operator, arrow;
 g := to1pf(f);
 dis := tolist(discont(g(x), x));
 return map(proc (p) options operator, arrow; [p, lrlim(g, p)] end proc, dis);
end proc;

reslist := proc (f)
 local dis, g, lr;
 options operator, arrow;
 g := to1pf(f);
 dis := tolist(discont(g(x), x));
 return map(proc (p) options operator, arrow; [p, res(g, p)] end proc, dis);
end proc;

denoms := proc (l) options operator, arrow; map(denom, l) end proc;

head := proc (l, n) options operator, arrow; return l[1 .. min(n, nops(l))] end proc;

tail := proc (l, n:=1) options arrow; return l[max(1, nops(l)-n+1) .. nops(l)] end proc;

ltrim := proc (l, n) options operator, arrow; return l[min(n+1, nops(l)+1) .. nops(l)] end proc;

rtrim := proc (l, n) options operator, arrow; return l[1 .. min(max(0, nops(l)-n), nops(l))] end proc;

ind := proc (x, rng::range) options operator, arrow; convert(piecewise(op(1, rng) < x and x < op(2, rng), 1, 0), Heaviside) end proc;

maxstep := unapply(convert(piecewise(y <= x, x, y), Heaviside), x, y);

minstep := unapply(convert(piecewise(x <= y, x, y), Heaviside), x, y);

grid := proc (r::range, n::posint)
     local rng, step, x, y;
     option operator;
     description "return a list of n points in r spaced by the range of r over n";
     x := op(1, r);
     y := op(2, r);
     rng := y-x;
     step := rng/n;
     return [seq(x+p*step, p = 0 .. n)];
end proc;

 translist := proc (l) options operator, arrow; convert(Transpose(Matrix(l)), listlist) end proc;

evalgf := proc (f::uneval, rng::range, n::posint)
       local l, points;
       options operator, arrow,remember;
       points := grid(rng, n);
       l := Matrix(map(f, points));
       #return l;
       return Matrix([translist([points, collist(l, 1)]), translist([points, collist(l, 2)]), translist([points, collist(l, 3)])]);
end proc;

longeval:=proc (f, rng, numpts, filename:="")
       if not is(filename="") then printf("logging to %s\n",filename) end if;
       return evalgf(proc (x) options operator, arrow; riaf(f, x, filename) end proc, rng, numpts, filename);
end proc;

tmatrix := proc (t::table)
Matrix(map(proc (p) t[p] end proc, sort(map(op, [indices(t)]))))
end proc;

 uniq := proc (x) local s; s := convert(x, set); return `intersect`(s, s) end proc;

riaf := proc (f::procedure, x, filename)
      local rlist, v, st;
      description "riaf(f,x,filename) return evalf([Re(f(x)),Im(f(x)),abs(f(x))]) and also append the result to filename";
      try
        st := time[real]();
  if not is(filename="") then appendto(filename) end if;
  v := f(x);
  if not type(v,complex) then
     v := evalf(v, _rest );
  end if;
  if not type(v,complex) then
     WARNING(sprintf("value at %a does not evaluate to a number, setting to 0", x) );
     v:=0;
  end if;
  rlist := [Re(v), Im(v), evalf(abs(v), _rest)];
	  printf("f(%+.5Zf)=[%+.20f,%+.20f,%+.20f] in %.2fs\n", x, rlist[1], rlist[2], rlist[3], time[real]()-st);
  return rlist;
      finally
            writeto(terminal);
      end try;
end proc;

savetable := proc (t::table, filename::string := sprintf("%s-table.mpl", evaln(t)))
  local table_contents_::table;
  description "save t to filename which defaults to t-table.mpl, returns filename";
  table_contents_ := t;
  save table_contents_, filename;
  return filename;
end proc;

loadtable := proc (filename::string)
  description "read table which was stored with savetable, if table_contents_ is not a table then error is thrown";
  read filename;
  if not type(table_contents_, table) then error "table_contents_ is not a table" end if;
  return op(table_contents_);
end proc;

remtable := proc (f::procedure) description "get remember table of procedure"; return op(4, eval(f)) end proc;

saveremtable := proc (f::procedure, filename::string := sprintf("%s-rtable.mpl", evaln(f)))
     description "saves the remember table of the procedure f to a file, returns filename";
     print(nops(remind(f)));
     return savetable(op(4, eval(f)), filename)
end proc;

merge := proc (destab::table, srctab::table)
      description "merge srctab into destab, return destab";
      map(proc (ent) destab[op(op(1, ent))] := op(op(2, ent)) end proc, translist([[indices(srctab)], [entries(srctab)]]));
      return destab;
end proc;

mergeremtable := proc(f::procedure,filename::string := sprintf("%s-rtable.mpl", evaln(f)))
      description "merge remember table of f, returns filename";
      local fun;
      fun := op(4,eval(f));
      if fun=NULL then
       error(sprintf("%s must have an entry in its remember table before merging",filename));
      end if;
      merge(fun,loadtable(filename));
      print(nops(remind(f)));
      return filename;
end proc;

listsum := proc (l::list)
local x, i;
description "get sum of all elements in a list";
x := 0;
for i from 1 to nops(l) do
    x := x+l[i];
        end do;
return x;
end proc;

signchanges := proc (l, f::procedure := eye)
    return map(proc (x) options operator, arrow; (1/2)*abs(x) end proc, sucdiff(map(proc (x) options operator, arrow; sign(f(x)) end proc, l)));
end proc;

rialistplot := proc (lst::list)
    options operator, arrow;
    description "return 3 plots of the list, red=Re, green=Im, blue=abs";
    return listplot(map(Re, lst), gridlines, color = red),
           listplot(map(Im, lst), gridlines, color = green),
           listplot(map(abs, lst), gridlines, color = blue);
end proc;

spd := proc (device, filename::string := "")
    description "plotsetup(device,plotoutput=filename,plotoptions=...";
    plotsetup(device, plotoutput = filename, plotoptions = cat("`", Join([_rest], ","), "`"));
end proc;

writegif := proc (plotlist::uneval, filename::string, plotopts::string := "")
 return writeimg(gif,plotlist,filename,plotopts,_rest);
end proc;

writeimg := proc (plotvar::string, device, filename::string, plotopts::string := "")
 local fmtstring, dispargs;
 description "returns a string to be used with parse, cause maple cant plot from procedures, even to a device. Trailing args are passed to display";
 dispargs := convert(Join([_rest], ","), string);
 if is(0 < Length(dispargs)) then dispargs := cat(",", dispargs) end if;
 fmtstring := "try spd(%s,%c%s%c,%c%s%c); display(%s%s); finally spd(default); end try;";
	 return sprintf(fmtstring, device, "\"", filename, "\"", "`", plotopts, "`", plotvar, dispargs);
end proc;

wb := proc(x, n)
     options operator, arrow;
     description "n-th component function of w(x)";
     n*(-1+x*n+x);
end proc:

w := `assuming`([unapply(eval(simp(wnpw(x, n)), {n = floor(1/x)}), x, proc_options = procopts)], [1/(n+1) < x and x < 1/n]);

remind := proc (f::procedure) options operator, arrow; [indices(remtable(f))] end proc;

pif := proc (x, l::list, height := 1, width := .1)
    description "return step function which equals height for x within distance width/2 of an element of the list and 0 otherwise";
    return listsum(map(proc (p) options operator, arrow; height*ind(x, p-(1/2)*width .. p+(1/2)*width) end proc, l));
end proc;

croots := proc (f, rrng::range, irng::range, srt::procedure:=imsort)
       description "wrapper around RootFinding[Analytic], returns list sorted by srt,defaults to imsort";
       return srt([Analytic((to1pf(f))(x), x, op(1,rrng)+I*op(1,irng) .. op(2,rrng)+I*op(2,irng),_rest)]);
end proc;

writeimages := proc (imglist::uneval, device, dir::string, prefix::string)
    local i, st;
    description "returns list of strings to be parsed do generate plots";
    st := "try ";
    for i to nops(eval(imglist)) do
    st := cat(st,timeval(writeimg(sprintf("%s[%d]", evaln(imglist), i), device, sprintf("%s/%s-%05d.%s", dir, prefix, i, device), _rest)));;
end do;
    st:=cat(st,"end try;");
    return st;
end proc;

rgb := [red, green, blue];

zetaf := proc (s)
      options remember, operator, arrow;
      return evalf(Zeta(s), _rest);
end proc;

zerof := proc (f::uneval, x)
      local g;
      description "return minimal function such that the roots are the same as the original function, zerof(f,x,...) returns the operand of RootOf(f(x)) and returns a function of arguments f(x,...)";
      g := to1pf(f);
      return unapply(eval(sort(op(1, RootOf(g(z_), z_)), z_), _Z = x), x, _rest);
end proc;

hsym := to1pf(floor(1/x));

imsort := proc(l::list)
     options operator, arrow;
     description "sort list by ascending imaginary part";
     return sort(l, proc (a, b) options operator, arrow; is(Im(a) < Im(b)) end proc);
end proc;

resort := proc(l::list)
     options operator, arrow;
     description "sort list by ascending real part";
     return sort(l, proc (a, b) options operator, arrow; is(Re(a) < Re(b)) end proc);
end proc;

relist := proc(l::list)
 return map(Re,l);
end proc;

imlist := proc(l::list)
 return map(Im,l);
end proc;

abslist := proc(l::list)
 return map(abs,l);
end proc;

findsublist := proc (subl::list, L::list)
     local i, len, matches;
     options operator, arrow;
     matches := [];
     len := nops(subl)-1;
     for i to nops(L)-len
     do
     if L[i .. i+len] = subl then matches := [op(matches), i] end if;
     end do;
     return matches;
end proc;

crootsn := proc (f, n::nonnegint, rrng, step::posint := 100)
options remember;
local irng;
irng := 1+n*step .. (1+(n+1)*step);
	printf("%a={Re=%a..%a,Im=%a..%a}\n", n, op(1, rrng), op(2, rrng), op(1, irng), op(2, irng));
return timeval(croots(to1pf(f), rrng, irng, imsort, _rest));
end proc;

crootlist := proc (f, rrng::range, nrng::range, step::posint := 100, fname::string:="")
  options remember;
  try
     if StringTools[Length](fname)>0 then appendto(fname) end if;
     return imsort(flist(proc (n) options operator, arrow; op(crootsn(f, n, rrng, _rest)) end proc, nrng));
  finally
     writeto(terminal);
  end try;
end proc;

pw := proc() return piecewise(_rest); end proc;

sucmax := proc (l::list, initv := 0)
       local maxv, retl, i;
       description "get running max of a list";
       retl := Array(flist(0, 1 .. nops(l)));
       maxv := initv;
       for i to nops(l)
       do
         if maxv < l[i] then maxv := l[i] end if;
  retl[i] := maxv;
       end do;
       return tolist(retl);
end proc;

topw := proc(x) return convert(x,piecewise,_rest); end proc;

torat := proc(x) return convert(x,rational,_rest); end proc;

cplot := proc (data)
      description "complexplot with boxed axes, gridlines, and point style";
      return complexplot(data, axes = boxed, gridlines, style = point, _rest );
end proc:

# asterisk, box, circle, cross, diagonalcross, diamond, point, solidbox, solidcircle, soliddiamond.
plotparams := [{color = red, symbol = circle},
       {color = green, symbol = box},
       {color = blue, symbol = cross },
       {color = orange, symbol = asterisk},
       {color = magenta, symbol = diagonalcross},
       {color = pink, symbol = diamond },
       {color = orange, symbol = solidbox },
       {color = navy, symbol = solidcircle },
       {color = cyan, symbol = soliddiamond } ]:

pp := proc(n) options operator,arrow; op(plotparams[n]); end proc;

end module;

savelib('cl',"cl.mla");

