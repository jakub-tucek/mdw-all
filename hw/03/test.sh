#!/bin/sh




#echo "------------------------------------------------------------------------------------"
#echo "Start session"
#curl 'http://localhost:7001/hw-03/book/create?fullName=JakubTucek&price=9999'

echo "------------------------------------------------------------------------------------"
echo "Get status"
curl http://localhost:7001/hw-03/book/status -H 'Cookie: JSESSIONID=12JGZwzFNQgpTbBqTPzDnQcVb6BnsCGlynDGRXH5nVm8Zh7SCZ3s!1230333371'

echo "------------------------------------------------------------------------------------"
echo "Access complete:"
curl http://localhost:7001/hw-03/book/complete

echo "------------------------------------------------------------------------------------"
echo "Pay"

curl http://localhost:7001/hw-03/book/pay?paymentId=14984321 -H 'Cookie: JSESSIONID=12JGZwzFNQgpTbBqTPzDnQcVb6BnsCGlynDGRXH5nVm8Zh7SCZ3s!1230333371'


echo "------------------------------------------------------------------------------------"
echo "Get status"
curl http://localhost:7001/hw-03/book/status -H 'Cookie: JSESSIONID=12JGZwzFNQgpTbBqTPzDnQcVb6BnsCGlynDGRXH5nVm8Zh7SCZ3s!1230333371'

echo "------------------------------------------------------------------------------------"
echo "Completed"
curl http://localhost:7001/hw-03/book/complete?paymentId=14984321 -H 'Cookie: JSESSIONID=12JGZwzFNQgpTbBqTPzDnQcVb6BnsCGlynDGRXH5nVm8Zh7SCZ3s!1230333371'

echo "------------------------------------------------------------------------------------"
echo "Get status"
curl http://localhost:7001/hw-03/book/status -H 'Cookie: JSESSIONID=12JGZwzFNQgpTbBqTPzDnQcVb6BnsCGlynDGRXH5nVm8Zh7SCZ3s!1230333371'