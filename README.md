## --------------------------------------------------EN en--------------------------------------------------

## The Problem

This is a username generator extracted from Oracle Identity Manager (IDM) files, which its function is to automatically create logins for new users into database within the company's rules. However this file is a little hard to understand which is the username that the generator is trying to create, it isn't creating within all the rules that we wanted and it only tries 3 times before stopping and having to someone create a login manually. One more thing is that it creates some usernames that can cause discomfort to users because the combinations create some improper usernames like "ANAL" for "Ana Lucia", for example.

## The Solution

I created a function that reduces the amount of IFs used to improve readability and probably performance (which isn't the focus, neither I tested it), and it tries more times when the fullname provided has more names in it. To manage the improper usernames, I created a blacklist file that the generator checks before returning so it won't generate any username that contains any of those blacklisted words.

## Rules:
1- Maximum of 20 and minimum of 3 characters
2- Outsourced users need to have a dot in the username

Not implemented yet:
3- Employee usernames can have only one of the names or even the initials as username
4- Trainee usernames will have a dot if the generator doesn't find any possibility without it

## --------------------------------------------------PT br--------------------------------------------------

## Problema

Este é um gerador de logins extraído dos arquivos do Oracle Identity Manager (IDM) em que a função é de criar automaticamente logins para os novos usuários dentro do banco de dados, de acordo com as regras da empresa. Entretanto, o arquivo é difícil de se entender sobre quais tentativas estão sendo feitas pelo gerador, além de não criar totalmente de acordo com as regras definidas e apenas tenta a criação 3 vezes antes de parar a execução e termos que criar um login manualmente. E mais uma coisa, este gerador pode criar alguns logins impróprios ou que causem desconforto, como "ANAL" para "Ana Lucia" por exemplo.

## Solução

Eu criei uma função que reduz a quantidade de IFs utilizados pelo programa para poder melhorar a legibilidade e provavelmente a performance (que não é o foco, e não testei isto), e também tenta mais vezes quando o nome completo possui mais sobrenomes. Para filtrar os logins impróprios, eu criei um arquivo de bloqueio que o gerador confere antes de retornar o login e impedindo que o login seja criado contendo qualquer uma das palavras bloqueadas.


## Regras:
1- Máximo de 20 e mínimo de 3 caracteres
2- Terceirizados possuem ponto no login

Ainda não implementadas:
3- Servidores podem ter login apenas com iniciais ou apenas 1 dos nomes
4- Estagiarios se não tiver mais possibilidades, tentar com ponto