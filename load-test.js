import http from 'k6/http';
import {check, sleep} from 'k6';

// Gera CPF único baseado no timestamp + VU (virtual user)
function generateCpf() {
    const timestamp = Date.now().toString().slice(-8);
    const vu = __VU.toString().padStart(3, '0');
    return `${timestamp}${vu}`.slice(0, 11);
}

function randomName() {
    const names = ['Ana Silva', 'Bruno Costa', 'Carla Souza', 'Diego Lima', 'Elena Rocha'];
    return names[Math.floor(Math.random() * names.length)];
}

export const options = {
    stages: [
        {duration: '30s', target: 10},  // sobe para 10 usuários
        {duration: '1m', target: 10},  // mantém 10 usuários por 1 min
        {duration: '30s', target: 0},  // desce para 0
    ],
    thresholds: {
        http_req_duration: ['p(95)<2000'], // 95% das requisições abaixo de 2s
        http_req_failed: ['rate<0.01'], // menos de 1% de erro
    },
};

export default function () {
    const payload = JSON.stringify({
        name: randomName(),
        cpf: generateCpf(),
        rg: '123456789',
        zipCode: '06172006',
        email: `user_${__VU}_${__ITER}@test.com`,
        telephone: '11947821278',
        description: 'cadastro',
        birthDate: '1993-07-26',
        monthlyIncome: 1000
    });

    const params = {
        headers: {'Content-Type': 'application/json'},
    };

    const res = http.post('http://localhost:8080/client/register', payload, params);

    check(res, {
        'status 201': (r) => r.status === 201,
        'tempo < 2s': (r) => r.timings.duration < 2000,
    });

    sleep(1);
}