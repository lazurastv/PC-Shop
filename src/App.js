import { useState } from 'react';
import './App.css';
import { ComputerAddUserForm } from './forms/ComputerAddUserForm';
import { ComputerForm } from './forms/ComputerForm';
import { GraphicsCardForm } from './forms/GraphicsCardForm';
import { MotherboardForm } from './forms/MotherboardForm';
import { ProcessorForm } from './forms/ProcessorForm';
import { UserForm } from './forms/UserForm';
import { ComputerList } from './tables/ComputerList';
import { GraphicsCardList } from './tables/GraphicsCardList';
import { MotherboardList } from './tables/MotherboardList';
import { ProcessorList } from './tables/ProcessorList';
import { UserList } from './tables/UserList';

function App() {
  const pages = ["Procesory", "Płyty główne", "Komputery", "Użytkownicy", "Karty graficzne"];
  const options = ["Wyświetl", "Dodaj", "Dodaj użytkownika"];
  const [visiblePage, setVisiblePage] = useState(0);
  const [visibleOption, setVisibleOption] = useState(0);
  return (
    <div className="app">
      <div className="buttons">
        {
          pages.map(page => <button key={page} onClick={() => setVisiblePage(pages.indexOf(page))}>{page}</button>)
        }
      </div>
      <div className='buttons'>
        <button onClick={() => setVisibleOption(0)}>Wyświetl</button>
        <button onClick={() => setVisibleOption(1)}>Dodaj</button>
        {
          visiblePage === 2 && <button onClick={() => setVisibleOption(2)}>Dodaj użytkownika</button>
        }
      </div>
      {
        visiblePage === 0 ? (!visibleOption ? <ProcessorList /> : <ProcessorForm />) :
          visiblePage === 1 ? (!visibleOption ? <MotherboardList /> : <MotherboardForm />) :
            visiblePage === 2 ? (!visibleOption ? <ComputerList /> : (visibleOption === 1 ? <ComputerForm /> : <ComputerAddUserForm />)) :
              visiblePage === 3 ? (!visibleOption ? <UserList /> : <UserForm />) :
                !visibleOption ? <GraphicsCardList /> : <GraphicsCardForm />
      }
    </div>
  );
}

export default App;
