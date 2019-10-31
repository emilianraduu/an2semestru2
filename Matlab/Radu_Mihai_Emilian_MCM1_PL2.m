%% ex1
A=[2,1,1;1,0,1;1,2,2];
n=length(A);
B=A;
I=eye(n);
coeficienti=zeros(1,n);

for i=1:n-1
    p=trace(B)/i;
    C=(B-p*I);
    B=A*(B-p*I);
    coeficienti(i)=p;
end
p=trace(B)/n;
coeficienti(n)=p;
disp(coeficienti);
T=(1/p)*C;
disp(T);

%% ex2

format short;
p(1)=1;
for i=1:n
    p(i+1)=coeficienti(i);
end
disp(p);
disp(roots(p));
der=polyder(p); 
radacini=roots(der); 
disp(radacini);
m=length(radacini); 
valori=zeros(1,m); 

for i=1:m
valori(i)=polyval(p,radacini(i));
end

disp(valori);

x=min(valori);
y=max(valori);

if x<0 && y<0
    disp("Nu exista radacini reale");
elseif x>0 && y>0
    disp("Nu exista radacini reale");
else
     while y-x>1  
        x=x+1;
        y=y-1;
     end
     if x<0 && y>0
     fprintf('Intervalul:%d,%d',x,y);
     else
     disp("Nu exista radacini reale"); 
     end
end