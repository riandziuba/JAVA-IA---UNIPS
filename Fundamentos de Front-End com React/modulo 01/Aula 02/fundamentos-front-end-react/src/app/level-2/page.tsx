import { CounterGlobal } from "@/components/CounterGlobal";
import { CounterGlobalValue } from "@/components/CounterGlobalValue";


const Page = async () => (
  <div>
    <h1 className="text-4xl font-bold">Pagina nível 2</h1>
    <CounterGlobal />
    <CounterGlobalValue />
  </div>
);


export default Page;
