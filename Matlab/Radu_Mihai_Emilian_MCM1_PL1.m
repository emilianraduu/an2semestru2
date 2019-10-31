%Se dau 2 nr naturale a,b. Sa se realizeze un fisier matlab de tip script in scopul obtinerii 
%altor 2 nr. intregi u,v pentru care cel mai mare divizior comun al nr a, b se expirma ca o combinatie liniara 
%dintre a si b cu coeficientii u,v fara euclid. //(a,b)=u*a+v*b

clc
clear all


a = input('Primul numar: ');
b = input('Al doilea numar: ');

u1 = 1;
u2 = 0;
u3 = a;
v1 = 0;
v2 = 1;
v3 = b;
q = 0;

while (v3 ~= 0) 
   
	q = floor(u3 / v3);
			
	temp = v1;
	v1 = u1 - q * v1;
	u1 = temp;
			
	temp = v2;
	v2 = u2 - q * v2;
	u2 = temp;
			
	temp = v3;
	v3 = u3 - q * v3;
	u3 = temp;
end

cmmdc = u3;
u = u1;
v = (cmmdc - u*a) / b;
fprintf('%d=gmd(a,b) = (%d) * %d + (%d) * %d',cmmdc, u,a,v,b);
   