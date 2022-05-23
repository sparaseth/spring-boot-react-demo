import React, { Component } from "react";
import { Link } from "react-router-dom";
import { Button, ButtonGroup, Container, Table } from "reactstrap";

class ClientList extends Component {

    constructor(props) {
        super(props);
        this.state = { clients: [] };
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch("/api/clients")
            .then(response => response.json())
            .then(data => this.setState({ clients: data }));
    }

    async remove(id) {
        await fetch(`/api/clients/${id}`, {
            method: "DELETE",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            }
        }).then(() => {
            let updatedClients = [...this.state.clients].filter(client => client.id !== id)
            this.setState({ clients: updatedClients });
        });
    }


    render() {

        const { clients } = this.state;

        const clientlist = clients.map(client => {
            return <tr key={client.id}>
                <td style={{ whiteSpace: "nowrap" }}>{client.name}</td>
                <td>{client.email}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/clients/" + client.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(client.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        })

        return (
            <div>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/clients/new">Add Client</Button>
                    </div>
                    <h3>Clients</h3>
                    <Table className="mt-4">
                        <thead>
                            <tr>
                                <th width="30%">Name</th>
                                <th width="30%">Email</th>
                                <th width="40%">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {clientlist}
                        </tbody>
                    </Table>
                </Container>
            </div>
        )
    }
}

export default ClientList;