# Projeto Cadastro de Clientes

A aplicação deve se capaz de receber, rever, gravar na base de dados e ainda alterar e/ou excluir os dados pessoais e endereos dos clientes.

## Tecnologia
* Java 20
* Java Fx
* Postgresql

### Descrição da atividade
Foi criada uma classe Controladora a fim de incluir os campos para capturar as informações dos clientes, além de uma tabela para exibir uma lista de clientes cadastrados.

No método executarSalvar deve ser possível salvar os dados dos clientes capturados, atualizando um cliente existente ou adicionando um novo.
Ele também também realiza verificações de validação e exibe alertas de erro, se necessário. Por fim, atualiza a exibição da lista de clientes e limpa os campos de texto.

O método executarExcluir é responsável por excluir registros de clientes e endereços. Ele verifica se há um índice válido selecionado e, em seguida, chama os métodos apropriados dos serviços para excluir os registros correspondentes. Em seguida, atualiza a exibição das tabelas de clientes e endereços, redefine a variável index e limpa os campos de texto.

A classe ConexaoDataBase é responsável por estabelecer a conexão com o banco de dados PostgreSQL

URL do banco de dados indica que o banco de dados está sendo acessado localmente (localhost), na porta padrão do PostgreSQL (5432), e com o nome do banco de dados trabalho.

Nome de usuário: "postgres". Isso indica o nome de usuário usado para autenticar a conexão.

Senha: "postgres". Isso indica a senha usada para autenticar a conexão.

A conexão é armazenada na variável conn e retornada pelo método.

A classe CadastroCliente e CadastroEndereço representam um objeto de cliente utilizado no sistema de cadastro, e possui um construtor adicional que permite criar uma instância da classe CadastroCliente e inicializar seus atributos ao mesmo tempo.

A classe inclui um construtor vazio (CadastroEndereco()) e um construtor que recebe todos os atributos como parâmetros.

Os métodos getter e setter também foram adicionados para cada atributo, permitindo acessar e modificar os valores dos atributos.

