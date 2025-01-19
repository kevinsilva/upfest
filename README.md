# UpFest
A Java Spring backend application for a Music Festival.

## Implementation Details

This project serves as the foundation for event management and cashless payments at music festivals.

#### Core Features

1. Event Management
- Manages stages, artists, tickets and concerts.
- Provides information about upcoming festivals.

2. Ticket Sales
- Allows users to buy tickets using banking references.
- Manages attendee's identification through email.

3. Cashless System
- Enables cashless payments at the festivals for consumables (foods and drinks).
- Allows participants to recharge their balance via payment references.
- Keeps detail transaction logs and balances.
- Manages vendors and their products.

#### Technology Stack
- **Language:** Java
- **Frameworks:** Spring Boot, Spring Data JPA
- **Libraries:** Jackson
- **Database:** MySQL
- **Build Tool:** Maven

## API Documentation
This section outlines the main APIs provided by the UpFest platform:

### Evento APIs
- **GET /events**: Fetch all events
- **POST /events**: Create a new event
- **POST /evento/criar**: Create a new event
- **POST /evento/:id_evento/editar**: Edit an existing event
- **GET /evento/listar**: List all events
- **POST /evento/:id_evento/palco/criar**: Add a stage to an event
- **POST /evento/:id_evento/palco/:id_palco/editar**: Edit an event's stage
- **GET /evento/:id_evento/palco/listar**: List stages of an event
- **POST /evento/:id_evento/series_bilhetes/criar**: Create a ticket series for an event
- **POST /evento/:id_evento/series_bilhetes/:id_serie/editar**: Edit a ticket series
- **GET /evento/:id_evento/series_bilhetes/listar**: List ticket series of an event
- **POST /evento/:id_evento/artistas/criar**: Add an artist to an event
- **POST /evento/:id_evento/artistas/:id_artista/editar**: Edit an event's artist
- **GET /evento/:id_evento/artistas/listar**: List artists of an event
- **POST /evento/:id_evento/concertos/criar**: Add a concert to an event
- **POST /evento/:id_evento/concertos/:id_concerto/editar**: Edit an event's concert
- **GET /evento/:id_evento/concertos/listar**: List concerts of an event

### Venda APIs
- **POST /vendas/bilhetes/comprar**: Purchase a ticket
- **POST /vendas/bilhetes/validar_entrada**: Validate event ticket entry
- **GET /vendas/participantes/listar?evento=:id_evento**: List event participants
- **GET /vendas/pagamentos/listar?participante=:email**: List payments of a participant
- **POST /vendas/pagamentos/validar**: Validate a ticket or cashless payment

### Cashless APIs
- **GET /cashless/:id_evento/saldo?participante=:email**: Retrieve cashless balance
- **GET /cashless/:id_evento/extrato?participante=:email**: Retrieve cashless transaction history
- **POST /cashless/:id_evento/registar_compra**: Register a cashless product purchase
- **POST /cashless/:id_evento/comerciantes/criar**: Create a merchant for an event
- **POST /cashless/:id_evento/comerciantes/:id_comerciante/editar**: Edit a merchant
- **GET /cashless/:id_evento/comerciantes/listar**: List merchants of an event
- **POST /cashless/:id_evento/comerciantes/:id_comerciante/produtos/criar**: Add a product for a merchant
- **POST /cashless/:id_evento/comerciantes/:id_comerciante/produtos/:id_produto/editar**: Edit a product for a merchant
- **GET /cashless/:id_evento/comerciantes/:id_comerciante/produtos/listar**: List products of a merchant

## Development
Follow these steps to set up the project:

```bash
git clone https://github.com/username/upfest.git
cd upfest
mvn clean install
```


> Configure the `application.properties` file with your MySQL credentials.

To run the application:

```bash
  mvn spring-boot:run
```

## Credits

- [Carla Nunes](https://github.com/CarlaRNunes)
- [Hugo Freire](https://github.com/Zeekcode)
- [Kevin Silva](https://github.com/kevinsilva)
- [Lúcia Barrela](https://github.com/LuciaBarrela)

## Licence

[MIT](https://choosealicense.com/licenses/mit/)