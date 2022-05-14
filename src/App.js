import { useState } from 'react';
import './App.css';
import { ComputerList } from './tables/ComputerList';
import { GPUList } from './tables/GPUList';
import { MotherboardList } from './tables/MotherboardList';
import { ProcessorList } from './tables/ProcessorList';
import { UserList } from './tables/UserList';

function App() {
  const pages = ["Procesory", "Płyty główne", "Komputery", "Użytkownicy", "Karty graficzne"];
  const [visiblePage, setVisiblePage] = useState(0);
  return (
    <div className="App">
      {
        pages.map(page => <button key={page} onClick={() => setVisiblePage(pages.indexOf(page))}>{page}</button>)
      }
      {
        visiblePage === 0 ?
          <ProcessorList /> :
          visiblePage === 1 ?
            <MotherboardList /> :
            visiblePage === 2 ?
              <ComputerList /> :
              visiblePage === 3 ?
                <UserList /> :
                <GPUList />
      }
    </div>
  );
}

export default App;
