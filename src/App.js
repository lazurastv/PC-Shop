import { useState } from 'react';
import './App.css';
import { ProcessorForm } from './forms/ProcessorForm';
import { ComputerList } from './tables/ComputerList';
import { GraphicsCardList } from './tables/GraphicsCardList';
import { MotherboardList } from './tables/MotherboardList';
import { ProcessorList } from './tables/ProcessorList';
import { UserList } from './tables/UserList';

function App() {
  const pages = ["Procesory", "Płyty główne", "Komputery", "Użytkownicy", "Karty graficzne"];
  const [visiblePage, setVisiblePage] = useState(0);
  const [addItem, setAddItem] = useState(false);
  return (
    <div className="app">
      <div className="buttons">
        {
          pages.map(page => <button key={page} onClick={() => setVisiblePage(pages.indexOf(page))}>{page}</button>)
        }
      </div>
      <div className='buttons'>
        <button onClick={() => setAddItem(false)}>Wyświetl</button>
        <button onClick={() => setAddItem(true)}>Dodaj</button>
      </div>
      {
        visiblePage === 0 ? (!addItem ? <ProcessorList /> : <ProcessorForm />) :
          visiblePage === 1 ?
            <MotherboardList /> :
            visiblePage === 2 ?
              <ComputerList /> :
              visiblePage === 3 ?
                <UserList /> :
                <GraphicsCardList />
      }
    </div>
  );
}

export default App;
